<?php

namespace AssureEntrepriseBundle\Repository;

use Doctrine\ORM\EntityRepository;

class AssureEntrepriseRepository extends EntityRepository
{

        public function DisableAE($emailentr){
        $q=$this->getEntityManager()->createQuery("update UserBundle:User u set u.enabled = false where u.email=:emailentr ")->setParameter('emailentr',$emailentr);

        return $q->getResult();
    }

    public function EnableAE($emailentr){
        $q=$this->getEntityManager()->createQuery("update UserBundle:User u set u.enabled = true where u.email=:emailentr ")->setParameter('emailentr',$emailentr);

        return $q->getResult();
    }


    public function findnom($email){
        $q=$this->getEntityManager()->createQuery("select a.nomentr from AssureEntrepriseBundle:AssureEntreprise a where a.emailentr=:email")->setParameter('email',$email);
        return $q->getResult();
    }

}
