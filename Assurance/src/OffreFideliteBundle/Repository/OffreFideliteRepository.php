<?php
/**
 * Created by PhpStorm.
 * User: ADMIN
 * Date: 26/03/2019
 * Time: 13:49
 */

namespace OffreFideliteBundle\Repository;
/**
 * OffreFideliteRepository
 */
use Doctrine\ORM\EntityRepository;

class OffreFideliteRepository extends \Doctrine\ORM\EntityRepository
{
    public function DerniereOffre()
    {
        $query=$this->getEntityManager()
            ->createQuery("Select Max(m.idoffre) as id from OffreFideliteBundle:Offrefidelite m")
            ->getResult();

        foreach ($query as $q)
            return $q["id"];


    }
}