<?php

namespace ExpertBundle\Controller;

use ExpertBundle\Form\ExpertType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use ExpertBundle\Entity\Expert;
use Symfony\Component\HttpFoundation\Request;
use SplFileObject;




class ExpertController extends Controller
{
    public function ajouterExpertAction(Request $request)
    {
        $expert = new Expert();
        $Form = $this->createForm(ExpertType::class, $expert);
        $Form->handleRequest($request);
        if ($Form->isSubmitted() && $Form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($expert);
            $em->flush();
            return $this->redirectToRoute('afficher_expert');

        }
        return $this->render('@Expert/Default/gestionExpert.html.twig', array('frm' => $Form->createView()));   //sexecute la premiere et apres if sexecute la 2eme

    }

    public function afficherExpertAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();

        $expert = $em->getRepository("ExpertBundle:Expert")->findAll();

        /**
         * @var $paginator \Knp\Component\Pager\Paginator
         */
        $paginator=$this->get('knp_paginator');
 $result=$paginator->paginate($expert,
     $request->query->getInt('page',1),
     $request->query->getInt('limit',5)

 );
        return $this->render('@Expert/Default/gestionExpert.html.twig', array('m' => $result));

    }

    public function supprimerExpertAction($idex)
    {
        $em = $this->getDoctrine()->getManager();
        $expert = $em->getRepository("ExpertBundle:Expert")->find($idex);
        $em->remove($expert);
        $em->flush();
        return $this->redirectToRoute('afficher_expert');
    }

    public function modifierExpertAction($idex, Request $request)
    {

        $em = $this->getDoctrine()->getManager();
        $expert = $em->getRepository("ExpertBundle:Expert")->find($idex);
        $Form = $this->createForm(ExpertType::class,$expert);
        $Form->handleRequest($request);
        if ($Form->isSubmitted()  && $Form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($expert);
            $em->flush();
            return $this->redirectToRoute('afficher_expert');
        }
        return $this->render('@Expert/Default/gestionExpert.html.twig',array('frm'=>$Form->createView()));

    }

    public function rechercherExpertAction(Request $request)
    {

        $em=$this->getDoctrine()->getManager();
        $valeur=$request->get('cinExpert');
        $expert =$em->getRepository('ExpertBundle:Expert')->findExpert($valeur);
        /**
         * @var $paginator \Knp\Component\Pager\Paginator
         */
        $paginator=$this->get('knp_paginator');
        $result=$paginator->paginate($expert,
            $request->query->getInt('page',1),
            $request->query->getInt('limit',5)

        );
        return $this->render('@Expert/Default/gestionExpert.html.twig', array('m' => $result));

    }
    public function exportAction()
    {
        $em=$this->getDoctrine()->getManager();
        $expert=$em->getRepository('ExpertBundle:Expert')->findAll();
        $writer=$this->container->get('egyg33k.csv.writer');

        $csv =$writer::createFromFileObject(new \SplTempFileObject());



        $csv->insertOne(['idex','cinex','faxex','nomex','prenomex','mailex','numeroex','adresseex','etatex','descriptionex']);
        foreach ($expert as $exp)
        {
            $csv->insertOne([$exp->getIdex(),$exp->getCinex(),$exp->getFaxex(),$exp->getNomex(),$exp->getPrenomex(),$exp->getMailex(),$exp->getNumeroex(),$exp->getAdresseex(),$exp->getEtatex(),$exp->getDescriptionex()]);
        }

        $csv->output('expert.csv');
        die('export');
    }



}
