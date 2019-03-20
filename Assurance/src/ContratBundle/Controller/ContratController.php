<?php

namespace ContratBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class ContratController extends Controller
{
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager()->getRepository('ContratBundle:Contrat');

        return $this->render('ContratBundle:Default:index.html.twig');
    }

   /* public function ajouterContrat(Contrat $cnt){
        $sql = "INSERT INTO Contrat (id_client,type,date_debut,date_echeance) VALUES (1,'Voiture',NULL,NULL)";
        try {
            $em = $this->getEntityManager()->getConnection()->exec($sql);
        } catch (DBALException $e) {
            echo "error";
        }
    }*/
}
