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

            $assentr->setNomentr($request->get('nomEntr'));
            $assentr->setEmailentr($request->get('emailEntr'));
            $assentr->setNumtelentr($request->get('numtelEntr'));
            $assentr->setAdresseentr($request->get('adresseEntr'));

            $em = $this->getDoctrine()->getManager();
            $em->persist($assentr);
            $em->flush();
            return $this->redirectToRoute('profile');
        }
        return $this->render('@AssureEntreprise\Default\ajoutAssEntr.html.twig',array());

    }
}
