<?php

namespace AssureParticulierBundle\Controller;

use AppBundle\Entity\AssureEntreprise;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use AssureParticulierBundle\Entity\AssureParticulier;
use Symfony\Component\HttpFoundation\Request;

class AssureParticulierController extends Controller
{
    public function indexAction()
    {
        return $this->render('@AssureParticulier/AssureParticulier/afficherAssurerParticlier.html.twig');
    }


    public function redirectPartFormAction(){
        return $this->render('@AssureParticulier\Default\choixduprofile.html.twig',array());
    }
    public function ajouterAssPartAction(Request $request)
    {

        $asspart = new AssureParticulier();

        if ($request->isMethod('POST')) {

            $asspart->setCin($request->get('cin'));
            $asspart->setNom($request->get('nom'));
            $asspart->setPrenom($request->get('prenom'));
            $asspart->setEmail($request->get('email'));
            $asspart->setNumtel($request->get('numtel'));

            $asspart->setAdresse($request->get('adresse'));

            $em = $this->getDoctrine()->getManager();
            $em->persist($asspart);
            $em->flush();
            return $this->redirectToRoute('profile');

        }
        return $this->render('@AssureParticulier\Default\ajoutAssPart.html.twig',array());

    }


    public function getcurrentUser($email)
    {

    }

    public function AssPartProfileDashboardAction(Request $request){
        $assure=new AssureParticulier();
        $em = $this->getDoctrine()->getManager();
        $assure = $em->getRepository('AssureParticulierBundle:AssureParticulier')->findOneBy(array("cin" => $assure->getCin()));
        if($assure != null){
            return $this->redirectToRoute('profile');
        }
        return $this->render('@AssureParticulier\Default\profile.html.twig',array('assure' => $assure));
    }



}
