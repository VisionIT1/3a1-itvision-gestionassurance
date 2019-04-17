<?php

namespace OffreBonConducteurBundle\Controller;

use OffreBonConducteurBundle\Entity\Offrebonconducteur;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use AffectationOffreBundle\Entity\Affectationoffre;

class OffreBCBController extends Controller
{

    public function formBCAction(Request $request)
    {
        $OffreBC = new Offrebonconducteur();
        $form = $this->createForm('OffreBonConducteurBundle\Form\OffrebonconducteurType', $OffreBC);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $OffreBC->setNumreglement(0);
            $OffreBC->setNbrannee(0);
            $em->persist($OffreBC);
            $em->flush();
            $OffreBC->setIdoffre($em->getRepository("OffreBonConducteurBundle:Offrebonconducteur")->DerniereOffre());
            $this->affecterOffre($OffreBC);
            return $this->redirectToRoute('OffreBC');}

        $em=$this->getDoctrine()->getManager();

        $OffreBCs=$em->getRepository("OffreBonConducteurBundle:Offrebonconducteur")->findAll();
        return $this->render('@OffreBonConducteur\OffreBCB.html.twig', array('form' => $form->createView(),'o'=>$OffreBCs));
    }
    public function modifierOBCBAction($id,Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $OffreBC=$em->getRepository("OffreBonConducteurBundle:Offrebonconducteur")->find($id);
        $Form=$this->createForm('OffreBonConducteurBundle\Form\OffrebonconducteurType',  $OffreBC);
        $Form->handleRequest($request);
        if($Form->isSubmitted()){
            $em = $this->getDoctrine()->getManager();
            $em->persist($OffreBC);
            $em->flush();
            return $this->redirectToRoute('OffreBC');
        }
        $em=$this->getDoctrine()->getManager();

        $OffreBCs=$em->getRepository("OffreBonConducteurBundle:Offrebonconducteur")->findAll();
        return $this->render('@OffreBonConducteur\OffreBCB.html.twig', array('form' => $Form->createView(),'o'=>$OffreBCs));

    }
    public function supprimerOBCBAction($id)
    {
        $em=$this->getDoctrine()->getManager();
        $OffreBC=$em->getRepository("OffreBonConducteurBundle:Offrebonconducteur")->find($id);
        $this->retirerOffre($OffreBC);
        $em->remove($OffreBC);
        $em->flush();
        return $this->redirectToRoute('OffreBC');
    }
    public function affecterOffre($O)
    {
        $em=$this->getDoctrine()->getManager();
        $query=$em->getRepository("BonusBundle:Bonus")->getBonus();
        foreach ($query as $q)
        {
            if(!$em->getRepository("AffectationOffreBundle:Affectationoffre")->assureExiste($q->getIdAssureur()))
            {
                $em->getRepository("ContratBundle:Contrat")->modifierNvPrimeOP($O->getPourcentagereduction(),$q->getIdContrat());
                $AffOffre=new Affectationoffre();
                $AffOffre->setIdoffre($O->getIdoffre());
                $AffOffre->setIdassure($q->getIdAssureur());
                $AffOffre->setTypeassure("assure particulier");
                $em->persist($AffOffre);
                $em->flush();
                $email=$em->getRepository("AssureParticulierBundle:AssureParticulier")->getMail($q->getIdAssureur());
                $this->sendMail($email);
            }
        }

    }
    public function retirerOffre($O)
    {
        $em=$this->getDoctrine()->getManager();
        echo $O->getIdoffre();
        $tab=$em->getRepository("AffectationOffreBundle:Affectationoffre")->assureOffre($O->getIdoffre());
        foreach ($tab as $a)
        {
            $em->getRepository("ContratBundle:Contrat")->annulerNvPrime($a->getIdassure());

            $em->remove($a);
            $em->flush();

        }
    }
    public function sendMail($email)
    {
        $message= \Swift_Message::newInstance()
            ->setSubject('Affectation Offre')
            ->setFrom(array('triplay24@gmail.com'=>"Assurance"))
            ->setTo($email)
            ->setCharset('utf-8')
            ->setContentType('text')
            ->setBody("Une offre bon conducteur vous a été affectée,vueillez récupérer votre nouveau contrat à l'agence");
        $this->get('mailer')->send($message);

    }
}
