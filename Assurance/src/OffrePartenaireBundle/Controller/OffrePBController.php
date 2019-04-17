<?php

namespace OffrePartenaireBundle\Controller;

use OffrePartenaireBundle\Entity\Offrepartenaire;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use AffectationOffreBundle\Entity\Affectationoffre;

class OffrePBController extends Controller
{
    public function formPBAction(Request $request)
    {
        $OffrePB = new Offrepartenaire();
        $form = $this->createForm('OffrePartenaireBundle\Form\OffrepartenaireType', $OffrePB);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($OffrePB);
            $em->flush();
            $OffrePB->setIdoffre($em->getRepository("OffrePartenaireBundle:OffrePartenaire")->DerniereOffre());
            $this->affecterOffre($OffrePB);
            return $this->redirectToRoute('OffrePB');}

        $em=$this->getDoctrine()->getManager();

        $OffrePBs=$em->getRepository("OffrePartenaireBundle:Offrepartenaire")->findAll();
        return $this->render('@OffrePartenaire\OffrePB.html.twig', array('form' => $form->createView(),'o'=>$OffrePBs));
    }
    public function modifierPBAction($id,Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $OffrePB=$em->getRepository("OffrePartenaireBundle:Offrepartenaire")->find($id);
        $Form=$this->createForm('OffrePartenaireBundle\Form\OffrepartenaireType',  $OffrePB);
        $Form->handleRequest($request);
        if($Form->isSubmitted()){
            $em = $this->getDoctrine()->getManager();
            $em->persist($OffrePB);
            $em->flush();
            return $this->redirectToRoute('OffrePB');
        }
        $em=$this->getDoctrine()->getManager();

        $OffrePBs=$em->getRepository("OffrePartenaireBundle:Offrepartenaire")->findAll();
        return $this->render('@OffrePartenaire\OffrePB.html.twig', array('form' => $Form->createView(),'o'=>$OffrePBs));

    }
    public function supprimerPBAction($id)
    {
        $em=$this->getDoctrine()->getManager();
        $OffrePB=$em->getRepository("OffrePartenaireBundle:Offrepartenaire")->find($id);
        $this->retirerOffre($OffrePB);
        $em->remove($OffrePB);
        $em->flush();
        return $this->redirectToRoute('OffrePB');
    }
    public function affecterOffre($O)
    {
        $em=$this->getDoctrine()->getManager();
        $tab=$em->getRepository("ContratBundle:Contrat")->typeVoiture();

        foreach($tab as $c)
        {
            if($c["cinAssure"]!=null && !$em->getRepository("AffectationOffreBundle:Affectationoffre")->assureExiste($c["cinAssure"]))
            {   $idMarque=$em->getRepository("VehiculeBundle:Vehicule")->getMarque($c["idType"]);
                $libelle=$em->getRepository("MarqueBundle:Marque")->getLibelle($idMarque);
                if(strcmp($libelle,$O->getPartenaire()->getLibelle())==0)
                {$em->getRepository("ContratBundle:Contrat")->modifierNvPrimeOP($O->getPourcentagereduction(),$c["id"]);
                $AffOffre=new Affectationoffre();
                $AffOffre->setIdoffre($O->getIdoffre());
                $AffOffre->setIdassure($c["cinAssure"]);
                $AffOffre->setTypeassure("assure particulier");
                $em->persist($AffOffre);
                $em->flush();
                $email=$em->getRepository("AssureParticulierBundle:AssureParticulier")->getMail($c["cinAssure"]);
                $this->sendMail($email);}

            }

        }
        $tab=$em->getRepository("ContratBundle:Contrat")->typeVoitureE();

        foreach($tab as $c)
        {
            if($c["nomentr"]!=null  && !$em->getRepository("AffectationOffreBundle:Affectationoffre")->assureExiste($c["nomentr"]))
            {  $idMarque=$em->getRepository("VehiculeBundle:Vehicule")->getMarque($c["idType"]);
                $libelle=$em->getRepository("MarqueBundle:Marque")->getLibelle($idMarque);
                if(strcmp($libelle,$O->getPartenaire()->getLibelle())==0)
                {$em->getRepository("ContratBundle:Contrat")->modifierNvPrimeOP($O->getPourcentagereduction(),$c["id"]);
                $AffOffre=new Affectationoffre();
                $AffOffre->setIdoffre($O->getIdoffre());
                $AffOffre->setIdassure($c["nomentr"]);
                $AffOffre->setTypeassure("assure entreprise");
                $em->persist($AffOffre);
                $em->flush();
                $email=$em->getRepository("AssureEntrepriseBundle:AssureEntreprise")->getMail($c["nomentr"]);

                $this->sendMail($email);}

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
            ->setBody("Une offre partenaire vous a été affectée,vueillez récupérer votre nouveau contrat à l'agence");
        $this->get('mailer')->send($message);

    }
}
