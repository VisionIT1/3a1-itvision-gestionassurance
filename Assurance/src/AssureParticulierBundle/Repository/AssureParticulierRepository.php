<?php

namespace AssureParticulierBundle\Repository;

use Doctrine\ORM\EntityRepository;

class AssureParticulierRepository extends EntityRepository
{

        public function DisableAP($email){
        $q=$this->getEntityManager()->createQuery("update UserBundle:User u set u.enabled = false where u.email=:email ")->setParameter('email',$email);

        return $q->getResult();
    }

    public function EnableAP($email){
        $q=$this->getEntityManager()->createQuery("update UserBundle:User u set u.enabled = true where u.email=:email ")->setParameter('email',$email);

        return $q->getResult();
    }


    public function findcin($email){
        $q=$this->getEntityManager()->createQuery("select a.cin from AssureParticulierBundle:AssureParticulier a where a.email=:email")->setParameter('email',$email);
        return $q->getResult();
    }


}
