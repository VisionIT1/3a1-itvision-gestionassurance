<?php

namespace ReparateurBundle\Controller;

use ReparateurBundle\Entity\Reparateur;
use ReparateurBundle\Form\ReparateurType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class ReparateurController extends Controller
{
    public function ajouterReparateurAction(Request $request)
    {
        $reparateur = new Reparateur();
        $Form = $this->createForm(ReparateurType::class,$reparateur);
        $Form->handleRequest($request);
        if ($Form->isSubmitted()  && $Form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($reparateur);
            $em->flush();
            return $this->redirectToRoute('afficher_reparateur');

        }
        return $this->render('@Reparateur/Default/gestionReparateur.html.twig', array('frm' => $Form->createView()));
    }
    public function afficheReparateurAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();

        $reparateur = $em->getRepository("ReparateurBundle:Reparateur")->findAll();
        /**
         * @var $paginator \Knp\Component\Pager\Paginator
         */
        $paginator=$this->get('knp_paginator');
        $result=$paginator->paginate($reparateur,
            $request->query->getInt('page',1),
            $request->query->getInt('limit',5)

        );
        return $this->render('@Reparateur/Default/gestionReparateur.html.twig', array('m' => $result));

    }

    public function supprimerReparateurAction($idrep)
    {
        $em = $this->getDoctrine()->getManager();
        $reparateur = $em->getRepository("ReparateurBundle:Reparateur")->find($idrep);
        $em->remove($reparateur);
        $em->flush();
        return $this->redirectToRoute('afficher_reparateur');
    }

    public function modifierReparateurAction($idrep, Request $request)
    {

        $em = $this->getDoctrine()->getManager();
        $reparateur = $em->getRepository("ReparateurBundle:Reparateur")->find($idrep);
        $Form = $this->createForm(ReparateurType::class,$reparateur);
        $Form->handleRequest($request);
        if ($Form->isSubmitted() && $Form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($reparateur);
            $em->flush();
            return $this->redirectToRoute('afficher_reparateur');
        }
        return $this->render('@Reparateur/Default/gestionReparateur.html.twig',array('frm'=>$Form->createView()));

    }
    public function rechercherReparateurAction(Request $request)
    {

        $em=$this->getDoctrine()->getManager();
        $valeur=$request->get('cinReparateur');
        $reparateur =$em->getRepository('ReparateurBundle:Reparateur')->findReparateur($valeur);
        /**
         * @var $paginator \Knp\Component\Pager\Paginator
         */
        $paginator=$this->get('knp_paginator');
        $result=$paginator->paginate($reparateur,
            $request->query->getInt('page',1),
            $request->query->getInt('limit',5)

        );
        return $this->render('@Reparateur/Default/gestionReparateur.html.twig', array('m' => $result));

    }
    public function exportAction()
    {
        $em=$this->getDoctrine()->getManager();
        $reparateur=$em->getRepository('ReparateurBundle:Reparateur')->findAll();
        $writer=$this->container->get('egyg33k.csv.writer');

        $csv =$writer::createFromFileObject(new \SplTempFileObject());



        $csv->insertOne(['idrep','cinrep','faxrep','nomrep','prenomrep','mailrep','numerorep','adresserep','etatrep','descriptionrep']);
        foreach ($reparateur as $rep)
        {
            $csv->insertOne([$rep->getIdrep(),$rep->getCinrep(),$rep->getFaxrep(),$rep->getNomrep(),$rep->getPrenomrep(),$rep->getMailrep(),$rep->getNumerorep(),$rep->getAdresserep(),$rep->getEtatrep(),$rep->getDescriptionrep()]);
        }

        $csv->output('reparateur.csv');
        die('export');
    }



}
