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


}
