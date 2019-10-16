<?php

namespace ContratBundle\Repository;

use Doctrine\ORM\EntityRepository;

class ContratRepository extends EntityRepository
{

        public function NouveauContrat(){
        $q=$this->getEntityManager()->createQuery("select c from ContratBundle:Contrat c where c.etat = 0");

            return $q->getResult();
        }

        public function ActiverContrat($id){
        $q=$this->getEntityManager()->createQuery("update ContratBundle:Contrat c set c.etat=1 where c.id=:id ")->setParameter('id',$id);

        return $q->getResult();
    }

    public function RechercherContratParticulier($cin){
        $q=$this->getEntityManager()->createQuery("select c from ContratBundle:Contrat c where c.etat=1 and c.cinAssure =:cin")->setParameter('cin',$cin);
    return $q->getResult();
    }

    public function RechercherContratEntreprise($nom){
        $q=$this->getEntityManager()->createQuery("select c from ContratBundle:Contrat c where c.etat=1 and c.nomentr =:nom")->setParameter('nom',$nom);
        return $q->getResult();
    }

    public function archive(){
        $date = new \DateTime();

        $q=$this->getEntityManager()->createQuery("select c from ContratBundle:Contrat c where c.dateEcheance = :date")->setParameter('date',$date->format('Y-m-d 00:00:00'));
       // $req=$this->getEntityManager()->createQuery("insert into c from ContratBundle:Contrat c where c.dateEcheance = CURDATE()");
        return $q->getResult();
    }

}
