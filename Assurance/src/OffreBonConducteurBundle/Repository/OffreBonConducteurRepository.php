<?php
/**
 * Created by PhpStorm.
 * User: ADMIN
 * Date: 31/03/2019
 * Time: 17:22
 */

namespace OffreBonConducteurBundle\Repository;
/**
 * OffreBonConducteurRepository
 */
use Doctrine\ORM\EntityRepository;

class OffreBonConducteurRepository extends \Doctrine\ORM\EntityRepository
{
    public function DerniereOffre()
    {
        $query=$this->getEntityManager()
            ->createQuery("Select Max(m.idoffre) as id from OffreBonConducteurBundle:Offrebonconducteur m")
            ->getResult();

        foreach ($query as $q)
            return $q["id"];


    }
}