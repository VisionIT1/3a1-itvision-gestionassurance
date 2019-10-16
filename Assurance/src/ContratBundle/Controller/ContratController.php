<?php

namespace ContratBundle\Controller;

use ContratBundle\Entity\Contrat;
use ContratBundle\Entity\ArchiveContrat;
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
        return $this->render('@Contrat/Contrat/affcontratadmin.html.twig');
    }

    public function ajouterContratAction(Request $request,$id_type,$typecon,$prime)
    {
        $user = $this->get('security.token_storage')->getToken()->getUser();
        $email=$user->getEmail();
        $em= $this->getDoctrine()->getManager();
        $ap=$em->getRepository('AssureParticulierBundle:AssureParticulier')->findcin($email);
        $ae=$em->getRepository('AssureEntrepriseBundle:AssureEntreprise')->findnom($email);

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
            return $this->redirectToRoute('ajoutsucces');


        }
        return $this->render('@Contrat\Default\ajoutContrat.html.twig',array("id"=>$id_type,"type"=>$typecon,"prime"=>$prime,"ap"=>$ap,"ae"=>$ae));


    }

    public function afficherSuccessAction(){
        return $this->render('@Contrat\Default\ajoutsucces.html.twig',array());

    }

    public function AfficherContratAction()
    {
        $em= $this->getDoctrine()->getManager();
        $contrats=$em->getRepository("ContratBundle:Contrat")->findAll();
        $newcontrats=$em->getRepository("ContratBundle:Contrat")->NouveauContrat();

        $arch=$em->getRepository("ContratBundle:Contrat")->archive();

        if($arch != null) {
            $archviecontrat=new ArchiveContrat();
            $datedebut = $arch[0]->getDateDebut();
            $dateecheance = $arch[0]->getDateEcheance();
            $datedebut = $datedebut->format('Y-m-d'); // for example
            $dateecheance = $dateecheance->format('Y-m-d'); // for example

            $archviecontrat->setNom($arch[0]->getNom());
            $archviecontrat->setDescription($arch[0]->getDescription());
            $archviecontrat->setCinAssure($arch[0]->getCinAssure());

            $archviecontrat->setNomentr($arch[0]->getNomentr());
            $archviecontrat->setType($arch[0]->getType());

            $archviecontrat->setIdType($arch[0]->getIdType());

            $archviecontrat->setDateDebut(new \DateTime($datedebut));

            $archviecontrat->setDateEcheance(new \DateTime($dateecheance));

            $archviecontrat->setEtat($arch[0]->getEtat());
            $archviecontrat->setPrime($arch[0]->getPrime());
            $archviecontrat->setNvprime($arch[0]->getNvprime());
            $em = $this->getDoctrine()->getManager();
            $em->persist($archviecontrat);
            $em->flush();
            foreach ($arch as $arch) {
                $em->remove($arch);
                $em->flush();
            }
            return $this->redirectToRoute('afficher_contrat_admin');

        }
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

    public function AfficherContratParticulierAction($cin){
        $em= $this->getDoctrine()->getManager();
        $cpart=$em->getRepository("ContratBundle:Contrat")->RechercherContratParticulier($cin);

        return $this->render('@Contrat\Default\afficherContratPart.html.twig', array(
            'cp' => $cpart));

    }


    public function AfficherContratEntrepriseAction($nom){
        $em= $this->getDoctrine()->getManager();
        $centr=$em->getRepository("ContratBundle:Contrat")->RechercherContratEntreprise($nom);

        return $this->render('@Contrat\Default\afficherContratEntr.html.twig', array(
            'ce' => $centr));


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

    public function archiverContratAction(Request $request){
        $em = $this->getDoctrine()->getManager();
        $contrats = $em->getRepository('ContratBundle:Contrat')->archive();


    }



}
