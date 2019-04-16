<?php

namespace SinistreBundle\Controller;

use AppBundle\Entity\AssureParticulier;
use PhpParser\Node\Scalar\String_;
use SinistreBundle\Entity\Sinistre;
use SinistreBundle\SinistreBundle;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\HttpFoundation\Request;
use SinistreBundle\Controller\SinistreRepository;
use PhpOffice\PhpSpreadsheet\Spreadsheet;
use PhpOffice\PhpSpreadsheet\Writer\Xlsx;
/**
 * Sinistre controller.
 *
 * @Route("sinistre")
 */
class SinistreController extends Controller
{
    /**
     * Lists all sinistre entities.
     *
     * @Route("/", name="sinistre_index")
     * @Method("GET")
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $sinistres = $em->getRepository('SinistreBundle:Sinistre')->findAll();

        return $this->render('sinistre/index.html.twig', array(
            'sinistres' => $sinistres,
        ));
    }


    public function affmodAction($id)
    {
        $em = $this->getDoctrine()->getManager();

        $sinistres = $this->getDoctrine()
            ->getRepository(Sinistre::class)
            ->findSin($id);


        return $this->render('@Sinistre\Default\modifierS.html.twig',array('c'=>$sinistres[0]["codeSinistre"],"now"=>$sinistres[0]["dateDeclaration"],'d'=>$sinistres[0]["dateSinistre"],"l"=>$sinistres[0]["lieuSinistre"],"dc"=>$sinistres[0]["dommageCorp"],"dm"=>$sinistres[0]["dommageMat"],"desc"=>$sinistres[0]["description"]));
    }
    /**
     * Creates a new sinistre entity.
     *
     * @Route("/new", name="sinistre_new")
     * @Method({"GET", "POST"})
     */
    public function newAction(Request $request)
    {
        $email= "".$this->getUser()->getEmail();
        $sinistre = new Sinistre();
        $codeAss= $this->getDoctrine()
            ->getRepository(Sinistre::class)
            ->findCin($email);
        $nbrSin= $this->getDoctrine()
            ->getRepository(Sinistre::class)
            ->findCountAllByCin($email);
        //var_dump($nbrSin);
        //$form = $this->createForm('SinistreBundle\Form\SinistreType', $sinistre);
        //$form->handleRequest($request);
        $sinistre->setCodeAssureur($codeAss[0]["cin"]);
        $dates = date('Y-m-d', strtotime($request->get('dates')));
        $dated = date('Y-m-d',time());
        $sinistre->setDateDeclaration(new \DateTime($dated));
        $sinistre->setDateSinistre(new \DateTime($dates));
        $sinistre->setCodeSinistre($request->get('codes'));
        $sinistre->setLieuSinistre($request->get('lieus'));
        $sinistre->setDommageCorp($request->get('dc'));
        $sinistre->setDommageMat($request->get('dm'));
        $sinistre->setNumeroSinistre($nbrSin[0][1]);
        $sinistre->setDescription($request->get('desc'));
        $sinistre->setEtat(0);
        if ($request->isMethod('POST')) {
            $em = $this->getDoctrine()->getManager();
            echo 'votre demande va etre envoyer au responsable';
            $em->persist($sinistre);
            $em->flush();
            $modele=$em->getRepository("SinistreBundle:Sinistre")->findBy(array('codeAssureur' => $codeAss[0]["cin"]));
            return $this->redirectToRoute("front_AfficherAllSpage");
            //this->Redirect(, false);
        }
        return $this->render('@Sinistre\Default\addNewSinistre.html.twig', array(
            'sinistre' => $sinistre,
            /*'form' => $form->createView(),*/
        ));
    }

    /**
     * Finds and h   ? ù*
     *
     * displays a sinistre entity.
     * @Route("/{codeSinistre}", name="sinistre_show")
     * @Method("GET")
     */
    public function showAction(Sinistre $sinistre)
    {
        $deleteForm = $this->createDeleteForm($sinistre);

        return $this->render('sinistre/show.html.twig', array(
            'sinistre' => $sinistre,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    public function importAction()
    {
        $email= "".$this->getUser()->getEmail();
        $codeAss= $this->getDoctrine()
            ->getRepository(Sinistre::class)
            ->findCin($email);
        $em=$this->getDoctrine()->getManager();
        $id=$em->getRepository("SinistreBundle:Sinistre")->findBy(array('codeAssureur' => $codeAss[0]["cin"],'etat' => 1));
        $spreadsheet = new Spreadsheet();
        $sheet = $spreadsheet->getActiveSheet();
        $sheet->setCellValue('A1', 'Date Degcaration');
        $sheet->setCellValue('B1', 'Date Sinistre');
        $sheet->setCellValue('C1', 'Code Sinistre');
        $sheet->setCellValue('D1', 'Lieu Sinistre');
        $sheet->setCellValue('E1', 'Dommage Corporelle');
        $sheet->setCellValue('F1', 'Dommage Materielle');
        $sheet->setCellValue('G1', 'Decription');
        $sheet->setCellValue('H1', 'Montant totale');
        $k=1;
        $somme=0;
        $ill=$em->getRepository("ReglementBundle:Reglement")->findBy(array('cinassureur' => $codeAss[0]["cin"]));
        foreach ($ill as $ii){
            $somme=$ii->getMontantRegl();
        }

        foreach ($id as  $i)
        {
            $k++;
            $sheet->setCellValue('A'.$k,$i->getDateDeclaration());
            $sheet->setCellValue('B'.$k,$i->getDateSinistre());
            $sheet->setCellValue('C'.$k, $i->getCodeSinistre());
            $sheet->setCellValue('D'.$k, $i->getLieuSinistre());
            $sheet->setCellValue('E'.$k, $i->getDommageCorp());
            $sheet->setCellValue('F'.$k, $i->getDommageMat());
            $sheet->setCellValue('G'.$k, $i->getDescription());
        }

        settype($somme, "string");
        $sheet->setCellValue('H'.$k, $somme);
        $writer = new Xlsx($spreadsheet);
        $writer->save($this->getUser()->getUsername().'.xlsx');



        return $this->redirectToRoute("front_AfficherAllSpage");

    }

    /**
     * Displays a form to edit an existing sinistre entity.
     *
     * @Route("/{codeSinistre}/edit", name="sinistre_edit")
     * @Method({"GET", "POST"})
     */
    public function editAction(Request $request)
    {


        if ($request->isMethod('POST')) {
            $dates = date('Y-m-d', strtotime($_POST['dates']));
            $em= $this->getDoctrine()->getManager();
            $model=$em->getRepository("SinistreBundle:Sinistre")->find($_POST["codes"]);
            $model->setDateSinistre(new \DateTime($dates));
            $model->setLieuSinistre($_POST["lieus"]);
            $model->setDommageMat($_POST["dm"]);
            $model->setDommageCorp($_POST["dc"]);
            $model->setDescription($_POST["desc"]);
            $em->persist($model);
            $em->flush();
            $this->getDoctrine()->getManager()->flush();
            return $this->redirectToRoute("front_AfficherAllSpage");
        }

        return $this->render('sinistre/edit.html.twig');
    }


    /*public function afficherAllAction()
    {
        return $this->render('SinistreBundle:Default:afficherS.html.twig');
    }*/
    public function showAllAction()
    {   $email= "".$this->getUser()->getEmail();
        $codeAss= $this->getDoctrine()
            ->getRepository(Sinistre::class)
            ->findCin($email);
        $em=$this->getDoctrine()->getManager();
        $modele=$em->getRepository("SinistreBundle:Sinistre")->findBy(array('codeAssureur' => $codeAss[0]["cin"],'etat' => 1));
        return $this->render('@Sinistre\Default\afficherS.html.twig',array('m'=>$modele));
    }


    /**
     * Deletes a sinistre entity.
     *
     * @Route("/{codeSinistre}", name="sinistre_delete")
     * @Method("DELETE")
     */
    public function deleteAction($id)
    {
            $sinistre =new Sinistre();
        $em=$this->getDoctrine()->getManager();
        $modele=$em->getRepository("SinistreBundle:Sinistre")->find($id);
            $em->remove($modele);
            $em->flush();
        $emm=$this->getDoctrine()->getManager();

        $this->getDoctrine()
            ->getRepository(Sinistre::class)
            ->del($id);





        return $this->redirectToRoute("front_AfficherAllSpage");
    }

    /**
     * Creates a form to delete a sinistre entity.
     *
     * @param Sinistre $sinistre The sinistre entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Sinistre $sinistre)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('sinistre_delete', array('codeSinistre' => $sinistre->getCodesinistre())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}
