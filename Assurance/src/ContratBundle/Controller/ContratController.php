<?php

namespace ContratBundle\Controller;

use ContratBundle\Entity\Contrat;
use ContratBundle\Form\ContratType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Console\Input\StringInput;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\HttpFoundation\Response;

class ContratController extends Controller
{
    public function indexAction()
    {
        return $this->render('@Contrat/Contrat/afficherContrat.html.twig');
    }

    public function ajouterContratAction(Request $request)
    {
       $datedebut=$request->get('date_debut');
        $dateecheance=$request->get('date_echeance');
        $Contrat = new Contrat();

        if ($request->isMethod('POST')) {

            $Contrat->setNom($request->get('nom'));
            $Contrat->setDescription($request->get('description'));
            $Contrat->setCinAssure($request->get('cin_assure'));
            $Contrat->setNomentr($request->get('nomEntr'));
            $Contrat->setType($request->get('type'));

            $Contrat->setIdType($request->get('id_type'));

            $Contrat->setDateDebut(new \DateTime($datedebut));

            $Contrat->setDateEcheance(new \DateTime($dateecheance));

            $Contrat->setEtat($request->get('etat'));
            $Contrat->setPrime($request->get('prime'));
            $Contrat->setNvprime($request->get('nvprime'));

            $em = $this->getDoctrine()->getManager();
            $em->persist($Contrat);
            $em->flush();
            return $this->redirectToRoute('ajoutSuccess');


        }
        return $this->render('@Contrat\Default\ajoutContrat.html.twig',array());


    }

    public function afficherSuccessAction(){
        return $this->render('@Contrat\Default\ajoutsucces.html.twig',array());

    }

    public function AfficherContratAction()
    {
        $em= $this->getDoctrine()->getManager();
        $contrats=$em->getRepository("ContratBundle:Contrat")->findAll();
        $newcontrats=$em->getRepository("ContratBundle:Contrat")->NouveauContrat();
        return $this->render('@Contrat\Default\affcontratadmin.html.twig', array("c"=>$contrats,"nouv"=>$newcontrats));
    }


    public function AfficherContratInactiveAction(){
        $em= $this->getDoctrine()->getManager();
        $active=$em->getRepository("ContratBundle:Contrat")->NouveauContrat();
        return $this->render('@Contrat\Default\toactivecontrat.html.twig', array("activ"=>$active));
    }

    public function ActiverContratAction($id){
        $em= $this->getDoctrine()->getManager();
        $ac=$em->getRepository("ContratBundle:Contrat")->ActiverContrat($id);
        return $this->redirectToRoute('afficher_contrat_admin');

    }

    public function modifierContratAction(Request $request,$id )
    {
        $em=$this->getDoctrine()->getManager();

        $contrat = $em->getRepository('ContratBundle:Contrat')->find($id);
        $datedebut=$request->get('date_debut');
        $dateecheance=$request->get('date_echeance');
        if ($request->isMethod('POST'))
        {
            $contrat->setNom($request->get('nom'));
            $contrat->setDescription($request->get('description'));
            $contrat->setCinAssure($request->get('cin_assure'));
            $contrat->setNomentr($request->get("nomEntr"));
            $contrat->setType($request->get('type'));
            $contrat->setIdType($request->get('id_type'));
            $contrat->setDateDebut(new \DateTime($datedebut));
            $contrat->setDateEcheance(new \DateTime($dateecheance));
            $contrat->setEtat($request->get('etat'));
            $contrat->setPrime($request->get('prime'));
            $em->persist($contrat);
            $em->flush();
            return $this->redirectToRoute('afficher_contrat_admin');

        }
        return $this->render('@Contrat\Default\modifierContratAdmin.html.twig', array(
            'contrat' => $contrat));


    }


    public function SupprimerContratAction($id) {
        $em = $this->getDoctrine()->getManager();
        $contrats = $em->getRepository('ContratBundle:Contrat')->find($id);

        if (!$contrats) {
            throw $this->createNotFoundException('No contrat found for id '.$id);
        }

        $em->remove($contrats);
        $em->flush();

        return $this->redirectToRoute('afficher_contrat_admin');
    }


    public function ajouterContratAdminAction(Request $request)
    {
        $datedebut=$request->get('date_debut');
        $dateecheance=$request->get('date_echeance');
        $Contrat = new Contrat();

        if ($request->isMethod('POST')) {

            $Contrat->setNom($request->get('nom'));
            $Contrat->setDescription($request->get('description'));
            $Contrat->setCinAssure($request->get('cin_assure'));
            $Contrat->setNomentr($request->get('nomEntr'));
            $Contrat->setType($request->get('type'));

            $Contrat->setIdType($request->get('id_type'));

            $Contrat->setDateDebut(new \DateTime($datedebut));

            $Contrat->setDateEcheance(new \DateTime($dateecheance));

            $Contrat->setEtat($request->get('etat'));
            $Contrat->setPrime($request->get('prime'));
            $Contrat->setNvprime($request->get('nvprime'));

            $em = $this->getDoctrine()->getManager();
            $em->persist($Contrat);
            $em->flush();

        }
        return $this->render('@Contrat\Default\ajoutContratAdmin.html.twig',array());


    }


}
