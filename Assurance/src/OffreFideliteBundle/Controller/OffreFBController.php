<?php

namespace OffreFideliteBundle\Controller;

use OffreFideliteBundle\Entity\Offrefidelite;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use AffectationOffreBundle\Entity\Affectationoffre;

class OffreFBController extends Controller
{
    public function formFBAction(Request $request)
    {
        $OffreFB = new Offrefidelite();
        $form = $this->createForm('OffreFideliteBundle\Form\OffrefideliteType', $OffreFB);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($OffreFB);
            $em->flush();
            $OffreFB->setIdoffre($em->getRepository("OffreFideliteBundle:Offrefidelite")->DerniereOffre());
            $this->affecterOffre($OffreFB);
            return $this->redirectToRoute('OffreFB');}

        $em=$this->getDoctrine()->getManager();

        $OffreFBs=$em->getRepository("OffreFideliteBundle:Offrefidelite")->findAll();
        return $this->render('@OffreFidelite\OffreFB.html.twig', array('form' => $form->createView(),'o'=>$OffreFBs));
    }
    public function modifierFBAction($id,Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $OffreFB=$em->getRepository("OffreFideliteBundle:Offrefidelite")->find($id);
        $Form=$this->createForm('OffreFideliteBundle\Form\OffrefideliteType',  $OffreFB);
        $Form->handleRequest($request);
        if($Form->isSubmitted()){
            $em = $this->getDoctrine()->getManager();
            $em->persist($OffreFB);
            $em->flush();
            return $this->redirectToRoute('OffreFB');
        }
        $em=$this->getDoctrine()->getManager();

        $OffreFBs=$em->getRepository("OffreFideliteBundle:Offrefidelite")->findAll();
        return $this->render('@OffreFidelite\OffreFB.html.twig', array('form' => $Form->createView(),'o'=>$OffreFBs));

    }
    public function supprimerFBAction($id)
    {
        $em=$this->getDoctrine()->getManager();
        $OffreFB=$em->getRepository("OffreFideliteBundle:Offrefidelite")->find($id);
        $this->retirerOffre($OffreFB);
        $em->remove($OffreFB);
        $em->flush();
        return $this->redirectToRoute('OffreFB');
    }
    public function affecterOffre($O)
    {
        $em=$this->getDoctrine()->getManager();
        $tab=$em->getRepository("ContratBundle:Contrat")->nbContratParAssure();

        foreach($tab as $c)
        {
                if($c["cinAssure"]!=null && $c["nb"]>=$O->getNbrcontratmin() && !$em->getRepository("AffectationOffreBundle:Affectationoffre")->assureExiste($c["cinAssure"]))
                {
                    $em->getRepository("ContratBundle:Contrat")->modifierNvPrime($O->getPourcentagereduction(),$c["cinAssure"]);
                    $AffOffre=new Affectationoffre();
                    $AffOffre->setIdoffre($O->getIdoffre());
                    $AffOffre->setIdassure($c["cinAssure"]);
                    $AffOffre->setTypeassure("assure particulier");
                    $em->persist($AffOffre);
                    $em->flush();
                    $email=$em->getRepository("AssureParticulierBundle:AssureParticulier")->getMail($c["cinAssure"]);

                    $this->sendMail($email);

                }

        }
        $tab=$em->getRepository("ContratBundle:Contrat")->nbContratParAssureE();

        foreach($tab as $c)
        {
            if($c["nomentr"]!=null && $c["nb"]>=$O->getNbrcontratmin() && !$em->getRepository("AffectationOffreBundle:Affectationoffre")->assureExiste($c["nomentr"]))
            {
                $em->getRepository("ContratBundle:Contrat")->modifierNvPrimeE($O->getPourcentagereduction(),$c["nomentr"]);
                $AffOffre=new Affectationoffre();
                $AffOffre->setIdoffre($O->getIdoffre());
                $AffOffre->setIdassure($c["nomentr"]);
                $AffOffre->setTypeassure("assure entreprise");
                $em->persist($AffOffre);
                $em->flush();
                $email=$em->getRepository("AssureEntrepriseBundle:AssureEntreprise")->getMail($c["nomentr"]);

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
        {  echo  strcmp($a->getTypeassure(),"assure particulier");
            if(strcmp($a->getTypeassure(),"assure particulier")==0)
            {$em->getRepository("ContratBundle:Contrat")->annulerNvPrime($a->getIdassure()); }
            if(strcmp($a->getTypeassure(),"assure entreprise")==0)
            {$em->getRepository("ContratBundle:Contrat")->annulerNvPrimeE($a->getIdassure()); }
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
            ->setBody("Une offre fidélitée vous a été affectée,vueillez récupérer votre nouveau contrat à l'agence");
        $this->get('mailer')->send($message);

    }
}
