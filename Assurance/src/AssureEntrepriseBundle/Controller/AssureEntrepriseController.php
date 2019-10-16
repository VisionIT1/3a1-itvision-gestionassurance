<?php

namespace AssureEntrepriseBundle\Controller;

use AssureEntrepriseBundle\Entity\AssureEntreprise;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class AssureEntrepriseController extends Controller
{
    public function indexAction()
    {
        return $this->render('AssureEntrepriseBundle:Default:index.html.twig');
    }

    public function redirectEntrForm(){
        return $this->render('@AssureEntreprise\Default\ajoutAssEntr.html.twig',array());
    }

    public function ajouterAssEntrAction(Request $request)
    {
        $assentr = new AssureEntreprise();


        if ($request->isMethod('POST')) {
            $user = $this->get('security.token_storage')->getToken()->getUser();
            $user->setType("entreprise");
            $assentr->setNomentr($request->get('nomEntr'));
            $assentr->setEmailentr($request->get('emailEntr'));
            $assentr->setNumtelentr($request->get('numtelEntr'));
            $assentr->setAdresseentr($request->get('adresseEntr'));
            $em = $this->getDoctrine()->getManager();
            $em->persist($assentr);
            $em->persist($user);
            $em->flush();


            return $this->redirectToRoute('profileEntr');
        }
        return $this->render('@AssureEntreprise\Default\ajoutAssEntr.html.twig',array());

    }

    public function AssEntrProfileDashboardAction(Request $request){

        $user = $this->get('security.token_storage')->getToken()->getUser();
        $email=$user->getEmail();
        $em = $this->getDoctrine()->getManager();
        $assentr = $em->getRepository('AssureEntrepriseBundle:AssureEntreprise')->findOneBy(array("emailentr" => $email));
        if($assentr != null){
            return $this->render('@AssureParticulier\Default\profile.html.twig',array('assentr' => $assentr));
        }

    }


    public function modifierEntrAction(Request $request)
    {
        $user = $this->get('security.token_storage')->getToken()->getUser();
        $email=$user->getEmail();
        $em=$this->getDoctrine()->getManager();
        $entr = $em->getRepository('AssureEntrepriseBundle:AssureEntreprise')->findOneBy(array("emailentr" => $email));
        if ($request->isMethod('POST'))
        {
            $entr->setNomentr($request->get('nomEntr'));
            $entr->setEmailentr($request->get('emailEntr'));
            $entr->setNumtelentr($request->get('numtelEntr'));
            $entr->setAdresseentr($request->get('adresseEntr'));
            $em = $this->getDoctrine()->getManager();
            $em->persist($entr);
            $em->persist($user);
            $em->flush();
            return $this->redirectToRoute('profileEntr');

        }
        return $this->render('@AssureEntreprise\Default\modifierAssEntr.html.twig', array(
            'e' => $entr));


    }

    public function AfficherAEAction()
    {
        $em= $this->getDoctrine()->getManager();
        $ae=$em->getRepository('AssureEntrepriseBundle:AssureEntreprise')->findAll();
        return $this->render('@AssureEntreprise\Default\affaeadmin.html.twig', array("ae"=>$ae));
    }


    public function modifierAEAdminAction(Request $request,$nomentr)
    {
        $em=$this->getDoctrine()->getManager();

        $eadmin = $em->getRepository('AssureEntrepriseBundle:AssureEntreprise')->find($nomentr);
        if ($request->isMethod('POST'))
        {
            $eadmin->setNomentr($request->get('nomentr'));
            $eadmin->setEmailentr($request->get('emailentr'));
            $eadmin->setNumtelentr($request->get('numtelentr'));
            $eadmin->setAdresseentr($request->get("adresseentr"));
            $em->persist($eadmin);
            $em->flush();

            return $this->redirectToRoute('afficher_ae_admin');
        }
        return $this->render('@AssureEntreprise\Default\modifierAEAdmin.html.twig', array(
            'aem' => $eadmin));


    }

    public function SupprimerAEAdminAction($nomentr) {
        $em = $this->getDoctrine()->getManager();
        $ae = $em->getRepository('AssureEntrepriseBundle:AssureEntreprise')->find($nomentr);

        if (!$ae) {
            throw $this->createNotFoundException('No assuré found for nom '.$nomentr);
        }

        $em->remove($ae);
        $em->flush();

        return $this->redirectToRoute('afficher_ae_admin');
    }

    public function AjouterAEAdminAction(Request $request)
    {

        $assentr = new AssureEntreprise();

        if ($request->isMethod('POST')) {

            $assentr->setNomentr($request->get('nomentr'));
            $assentr->setEmailentr($request->get('emailentr'));
            $assentr->setNumtelentr($request->get('numtelentr'));
            $assentr->setAdresseentr($request->get("adresseentr"));
            $em = $this->getDoctrine()->getManager();
            $em->persist($assentr);
            $em->flush();
            return $this->redirectToRoute('afficher_ae_admin');

        }
        return $this->render('@AssureEntreprise\Default\ajouterAEadmin.html.twig',array());

    }

    public function desactiverAEAction($emailentr){
        $em= $this->getDoctrine()->getManager();
        $ae=$em->getRepository('AssureEntrepriseBundle:AssureEntreprise')->DisableAE($emailentr);
        return $this->redirectToRoute('afficher_ae_admin');

    }

    public function activerAEAction($emailentr){
        $em= $this->getDoctrine()->getManager();
        $ae=$em->getRepository('AssureEntrepriseBundle:AssureEntreprise')->EnableAE($emailentr);
        return $this->redirectToRoute('afficher_ae_admin');

    }


}
