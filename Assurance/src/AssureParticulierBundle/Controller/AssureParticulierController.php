<?php

namespace AssureParticulierBundle\Controller;

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
            $user = $this->get('security.token_storage')->getToken()->getUser();
            $user->setType("particulier");
            $asspart->setCin($request->get('cin'));
            $asspart->setNom($request->get('nom'));
            $asspart->setPrenom($request->get('prenom'));
            $asspart->setEmail($request->get('email'));
            $asspart->setNumtel($request->get('numtel'));
            $asspart->setAdresse($request->get('adresse'));
            $em = $this->getDoctrine()->getManager();
            $em->persist($asspart);
            $em->persist($user);
            $em->flush();
            return $this->redirectToRoute('profile');

        }
        return $this->render('@AssureParticulier\Default\ajoutAssPart.html.twig',array());

    }


    public function getcurrentUser($email)
    {

    }

    public function AssPartProfileDashboardAction(Request $request){

        $user = $this->get('security.token_storage')->getToken()->getUser();
        $email=$user->getEmail();
        $em = $this->getDoctrine()->getManager();
        $assure = $em->getRepository('AssureParticulierBundle:AssureParticulier')->findOneBy(array("email" => $email));
        if($assure != null){
            return $this->render('@AssureParticulier\Default\profile.html.twig',array('assure' => $assure));
        }
    }

    public function showprofileAction(Request $request){

        return $this->render('@AssureParticulier\Default\profile.html.twig');
    }


    public function modifierPartAction(Request $request)
    {
        $user = $this->get('security.token_storage')->getToken()->getUser();
        $email=$user->getEmail();
        $em=$this->getDoctrine()->getManager();
        $part = $em->getRepository('AssureParticulierBundle:AssureParticulier')->findOneBy(array("email" => $email));
        if ($request->isMethod('POST'))
        {
            $part->setCin($request->get('cin'));
            $part->setNom($request->get('nom'));
            $part->setPrenom($request->get('prenom'));
            $part->setEmail($request->get("email"));
            $part->setNumtel($request->get('numtel'));
            $part->setAdresse($request->get('adresse'));
            $em->persist($part);
            $em->flush();
            return $this->redirectToRoute('profile');

        }
        return $this->render('@AssureParticulier\Default\modifierAssPart.html.twig', array(
            'p' => $part));

    }

    public function AfficherContratParticulierAction($cin){
        $em= $this->getDoctrine()->getManager();
        $cpart=$em->getRepository("ContratBundle:Contrat")->RechercherContratParticulier($cin);
        return $this->redirectToRoute('afficher_contrat_admin');

    }

    public function AfficherAPAction()
    {
        $em= $this->getDoctrine()->getManager();
        $ap=$em->getRepository('AssureParticulierBundle:AssureParticulier')->findAll();
        return $this->render('@AssureParticulier\Default\affapadmin.html.twig', array("ap"=>$ap));
    }


    public function modifierAPAdminAction(Request $request,$cin )
    {
        $em=$this->getDoctrine()->getManager();

        $padmin = $em->getRepository('AssureParticulierBundle:AssureParticulier')->find($cin);
        if ($request->isMethod('POST'))
        {
            $padmin->setCin($request->get('cin'));
            $padmin->setNom($request->get('nom'));
            $padmin->setPrenom($request->get('prenom'));
            $padmin->setEmail($request->get("email"));
            $padmin->setNumtel($request->get('numtel'));
            $padmin->setAdresse($request->get('adresse'));
            $em->persist($padmin);
            $em->flush();

            return $this->redirectToRoute('afficher_ap_admin');
        }
        return $this->render('@AssureParticulier\Default\modifierAPAdmin.html.twig', array(
            'apm' => $padmin));


    }

    public function SupprimerAPAdminAction($cin) {
        $em = $this->getDoctrine()->getManager();
        $ap = $em->getRepository('AssureParticulierBundle:AssureParticulier')->find($cin);

        if (!$ap) {
            throw $this->createNotFoundException('No assuré found for cin '.$cin);
        }

        $em->remove($ap);
        $em->flush();

        return $this->redirectToRoute('afficher_ap_admin');
    }


    public function AjouterAPAdminAction(Request $request)
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
            return $this->redirectToRoute('afficher_ap_admin');

        }
        return $this->render('@AssureParticulier\Default\ajoutapadmin.html.twig',array());

    }

    public function desactiverAPAction($email){
        $em= $this->getDoctrine()->getManager();
        $ap=$em->getRepository('AssureParticulierBundle:AssureParticulier')->DisableAP($email);
        return $this->redirectToRoute('afficher_ap_admin');

    }

    public function activerAPAction($email){
        $em= $this->getDoctrine()->getManager();
        $ap=$em->getRepository('AssureParticulierBundle:AssureParticulier')->EnableAP($email);
        return $this->redirectToRoute('afficher_ap_admin');

    }



}
