<?php

namespace BonusBundle\Controller;

use BonusBundle\Form\BonusType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use BonusBundle\Entity\Bonus;
use ContratBundle\Entity\Contrat;

class BonusController extends Controller
{
    public function rechercherBonusAction(Request $request)
    {

        $em=$this->getDoctrine()->getManager();

        $contrat =$em->getRepository('BonusBundle:Bonus')->findContratPourBonus();

        foreach ($contrat as $c=>$value)
        {

            if( $em->getRepository("BonusBundle:Bonus")->findBy(array('id_contrat'=>$value->getId()))!=null)
            {
              unset($contrat[$c]);
            }

        }
        /**
         * @var $paginator \Knp\Component\Pager\Paginator
         */
        $paginator=$this->get('knp_paginator');
        $result=$paginator->paginate($contrat,
            $request->query->getInt('page',1),
            $request->query->getInt('limit',5)

        );
        return $this->render('@Bonus/Default/gestionBonus.html.twig', array('m' => $result));

    }




    public function ajouterBonusAction($id,Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $contrat = $em->getRepository("ContratBundle:Contrat")->find($id);
        $bonus = new Bonus();
        $bonus->setIdContrat($contrat->getId());
        $bonus->setIdAssureur($contrat-> getCinAssure());
       $Form = $this->createForm(BonusType::class,$bonus);
        $Form->handleRequest($request);
        if ($Form->isSubmitted()) {
            $em = $this->getDoctrine()->getManager();


            $em->persist($bonus);
            $em->flush();

            return $this->redirectToRoute('afficher_Listebo');
        }
        return $this->render('@Bonus/Default/gestionBonus.html.twig', array('frm' => $Form->createView()));

    }

    public function rechercherBonusEcheableAction(Request $request)
    {


        $em=$this->getDoctrine()->getManager();

        $bonus =$em->getRepository('BonusBundle:Bonus')->findBonusDuContratEcheable();

            return $this->render('@Bonus/Default/gestionBonus.html.twig', array('c' => $bonus));


    }
    public function calculerScoreAction($id_bonus,Request $request)
    {

        $em = $this->getDoctrine()->getManager();
        $bonus = $em->getRepository("BonusBundle:Bonus")->find($id_bonus);


        $nombre=$em->getRepository('BonusBundle:Bonus')->calculerScorePourBonus($id_bonus);


        if($nombre[0][1]!=0)
        {

            $bonus->setScore($bonus->getScore()*($nombre[0][1])*1.25);
            $bonus->setEtat('0');

        }
        else if ($nombre[0][1]==0)
        {

            $bonus->setScore($bonus->getScore()*0.95);
            $bonus->setEtat('1');
        }
        $Form = $this->createForm(BonusType::class,$bonus);
        $Form->handleRequest($request);
        if ($Form->isSubmitted()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($bonus);
            $em->flush();
            return $this->redirectToRoute('afficher_Listebo');

        }
        return $this->render('@Bonus/Default/gestionBonus.html.twig', array('frm' => $Form->createView()));
    }

    public function afficherBonusFrontAction($cin)
    {

        $em = $this->getDoctrine()->getManager();
        $bonus =$em->getRepository("BonusBundle:Bonus")->afficherBonusFront($cin);
            return $this->render('@Bonus/Default/gestionBonusFront.html.twig', array(
            'm' => $bonus));

    }



    public function afficherListeBoAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();

        $bonus = $em->getRepository("BonusBundle:Bonus")->findAll();

        /**
         * @var $paginator \Knp\Component\Pager\Paginator
         */
        $paginator=$this->get('knp_paginator');
        $result=$paginator->paginate($bonus,
            $request->query->getInt('page',1),
            $request->query->getInt('limit',5)

        );
        return $this->render('@Bonus/Default/gestionBonus.html.twig', array('n' => $result));

    }







}
