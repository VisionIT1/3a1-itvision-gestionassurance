<?php

namespace ReglementBundle\Controller;

use BackBundle\BackBundle;
use ReglementBundle\Entity\Reglement;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\HttpFoundation\Request;

/**
 * Reglement controller.
 *
 * @Route("reglement")
 */
class ReglementController extends Controller
{
    /**
     * Lists all reglement entities.
     *
     * @Route("/", name="reglement_index")
     * @Method("GET")
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $reglements = $em->getRepository('ReglementBundle:Reglement')->findAll();

        return $this->render('reglement/index.html.twig', array(
            'reglements' => $reglements,
        ));
    }

    /**
     * Creates a new reglement entity.
     *
     * @Route("/new", name="reglement_new")
     * @Method({"GET", "POST"})
     */
    public function newAction(Request $request)
    {
        $reglement = new Reglement();
        $form = $this->createForm('ReglementBundle\Form\ReglementType', $reglement);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($reglement);
            $em->flush();

            return $this->redirectToRoute('reglement_show', array('codeRegl' => $reglement->getCoderegl()));
        }

        return $this->render('reglement/new.html.twig', array(
            'reglement' => $reglement,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a reglement entity.
     *
     * @Route("/{codeRegl}", name="reglement_show")
     * @Method("GET")
     */
    public function showNewSinAction()
    {

        $em=$this->getDoctrine()->getManager();
        $modele=$em->getRepository("SinistreBundle:Sinistre")->findBy(array('etat' => 0));
        return $this->render('@Reglement\Default\consulterNouvSin.html.twig',array('c'=>$modele));
    }
    public function ShowAllSinAction()
    {

        $em=$this->getDoctrine()->getManager();
        $modele=$em->getRepository("SinistreBundle:Sinistre")->findAll();
        return $this->render('@Reglement\Default\consulterAllSin.html.twig',array('c'=>$modele));
    }
    public function AffEditAction(Request $request)
    {
        if ($request->isMethod('POST'))
        {
            $dates = date('Y-m-d', strtotime($_POST['dates']));
            $em = $this->getDoctrine()->getManager();
            $model = $em->getRepository("SinistreBundle:Sinistre")->find($_POST["codes"]);
            $model->setDateSinistre(new \DateTime($dates));
            $model->setLieuSinistre($_POST["lieus"]);
            $model->setDommageMat($_POST["dm"]);
            $model->setDommageCorp($_POST["dc"]);
            $model->setDescription($_POST["desc"]);
            $em = $this->getDoctrine()->getManager();
            $em->persist($model);
            $em->flush();
            return $this->redirectToRoute("ShowAllSin");
        }
        return $this->render('sinistre/edit.html.twig');
    }
    public function RejeterNewSinAction($id)
    {

        $em=$this->getDoctrine()->getManager();
        $modele=$em->getRepository("SinistreBundle:Sinistre")->find($id);
        $em->remove($modele);
        $em->flush();
        return $this->redirectToRoute("Consulter_NouvSin");
    }

    public function SupprimerSinAction($id)
    {

        $em=$this->getDoctrine()->getManager();
        $modele=$em->getRepository("SinistreBundle:Sinistre")->find($id);
        $em->remove($modele);
        $em->flush();
        return $this->redirectToRoute("ShowAllSin");
    }
    public function ValiderNewSinAction($id){
        $em=$this->getDoctrine()->getManager();

        $model=$em->getRepository("SinistreBundle:Sinistre")->find($id);
        $rep=$em->getRepository("AppBundle:Reparateur")->findby(array('etatrep'=>'oui'));
        $ex=$em->getRepository("AppBundle:Expert")->findby(array('etatex'=>'oui'));

        //$model->setEtat(1);
        $em->persist($model);
        $em->flush();
        return $this->render('@Reglement\Default\AjouterRegl.html.twig',array('c'=>$model,'ex'=>$ex,'rep'=>$rep));
    }

    public function AffreglAction(Request $request){

        if ($request->isMethod('POST'))
        {
            $dates = date('Y-m-d', strtotime($_POST['dater']));
            $em = $this->getDoctrine()->getManager();
            $model= new Reglement();
            $model->setDateRegl(new \DateTime($dates));
            $model->setFraisExpert($_POST["fe"]);
            $model->setModeRegl($_POST["mode"]);
            $model->setMontantRegl($_POST["mntr"]);
            $model->setCinassureur($_POST["ca"]);
            $model->setIdex($_POST["idEx"]);
            $model->setIdrep($_POST["idRep"]);
            $model->setCodeSin($_POST["cs"]);
            $em->persist($model);
            $em->flush();
            $kk = $this->getDoctrine()->getManager();
            $modele=$kk->getRepository("SinistreBundle:Sinistre")->find($model->getCodeSin());
            $modele->setEtat(1);
            $em->persist($model);
            $em->flush();
            $this->getDoctrine()->getManager()->flush();
            return $this->redirectToRoute("Consulter_NouvSin");
        }
        return $this->render('sinistre/edit.html.twig');
    }


    public function ModifierReglAction(Request $request,$id )
    {

        $em=$this->getDoctrine()->getManager();

        $modele=$em->getRepository("ReglementBundle:Reglement")->find($id);
            var_dump($modele);
        $rep=$em->getRepository("AppBundle:Reparateur")->findby(array('etatrep'=>'oui'));
        $ex=$em->getRepository("AppBundle:Expert")->findby(array('etatex'=>'oui'));

        if ($request->isMethod('POST')) {
            $em= $this->getDoctrine()->getManager();
            $model=$em->getRepository("ReglementBundle:Reglement")->findby(array('codeRegl'=>$id));
            var_dump($model);
            $modele->setFraisExpert($_POST["fe"]);
            $modele->setModeRegl($_POST["mode"]);
            $modele->setMontantRegl($_POST["mntr"]);
            $modele->setCinassureur($_POST["ca"]);
            $modele->setIdex($_POST["idEx"]);
            $modele->setIdrep($_POST["idRep"]);
            $modele->setCodeSin($_POST["cr"]);
            $em->persist($modele);
            $em->flush();
            $this->getDoctrine()->getManager()->flush();
            return $this->redirectToRoute("AffAllregl");
        }
        $em->flush();
        return $this->render('@Reglement\Default\ModiferRegl.html.twig',array('k'=>$modele,'ex'=>$ex,'rep'=>$rep));
    }


    public function AffEditSinPageAction($id)
    {
        $em = $this->getDoctrine()->getManager();

        $sinistres =$em->getRepository("SinistreBundle:Sinistre")->find($id);
        return $this->render('@Reglement\Default\modifierS.html.twig',array('c'=>$sinistres));
    }
    public function reportAction(Request $request)
    {
        if(isset($_POST["repport"])){
        $ob= fopen('report.html','a+');
        fwrite($ob,$_POST["repport"]);
        fclose($ob);
        return $this->redirect("http://127.0.0.1:8000/admindashboard");
        }
        return $this->render('@Reglement\Default\rapport.html.twig');
    }

    public function AffAllreglAction()
    {
        $em = $this->getDoctrine()->getManager();
        $sinistres =$em->getRepository("ReglementBundle:Reglement")->findAll();
        return $this->render('@Reglement\Default\afficherR.html.twig',array('c'=>$sinistres));
    }
    /**
     * Displays a form to edit an existing reglement entity.
     *
     * @Route("/{codeRegl}/edit", name="reglement_edit")
     * @Method({"GET", "POST"})
     */
    public function DeleteReglAction($id)
    {
            $em = $this->getDoctrine()->getManager();

        $reglement=$em->getRepository("ReglementBundle:Reglement")->find($id);
            $em->remove($reglement);
            $em->flush();


        return $this->redirectToRoute('AffAllregl');
    }

    public function editAction(Request $request, Reglement $reglement)
    {
        $deleteForm = $this->createDeleteForm($reglement);
        $editForm = $this->createForm('ReglementBundle\Form\ReglementType', $reglement);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('reglement_edit', array('codeRegl' => $reglement->getCoderegl()));
        }

        return $this->render('reglement/edit.html.twig', array(
            'reglement' => $reglement,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a reglement entity.
     *
     * @Route("/{codeRegl}", name="reglement_delete")
     * @Method("DELETE")
     */
    public function deleteAction(Request $request, Reglement $reglement)
    {
        $form = $this->createDeleteForm($reglement);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($reglement);
            $em->flush();
        }

        return $this->redirectToRoute('reglement_index');
    }

    /**
     * Creates a form to delete a reglement entity.
     *
     * @param Reglement $reglement The reglement entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Reglement $reglement)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('reglement_delete', array('codeRegl' => $reglement->getCoderegl())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}
