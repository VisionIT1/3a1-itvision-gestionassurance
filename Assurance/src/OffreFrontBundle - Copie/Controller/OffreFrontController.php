<?php

namespace OffreFrontBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class OffreFrontController extends Controller
{
    public function afficherOffreAction()
    {

        $em=$this->getDoctrine()->getManager();

        $OffreBCs=$em->getRepository("OffreBonConducteurBundle:Offrebonconducteur")->findAll();
        $OffreFBs=$em->getRepository("OffreFideliteBundle:Offrefidelite")->findAll();
        $OffrePBs=$em->getRepository("OffrePartenaireBundle:Offrepartenaire")->findAll();
        return $this->render('@OffreFront\OffreFront.html.twig', array('o1'=>$OffreBCs,'o2'=>$OffreFBs,'o3'=>$OffrePBs));

    }
}
