<?php
/**
 * Created by PhpStorm.
 * User: HP
 * Date: 24/03/2019
 * Time: 17:32
 */

namespace ExpertBundle\Repository;


class ExpertRepository extends \Doctrine\ORM\EntityRepository
{
public function findExpert($valeur)
{

    //$query=$this->createQueryBuilder('e')->where('e.nomex like :nomex')->setParameter('nomex','%'.$valeur.'%')->orderBy('e.nomex','ASC')->getQuery();
        $query=$this->createQueryBuilder('e')->where('e.nomex like :nomex or e.cinex=:cinex or e.adresseex like :adresseex or e.etatex=:etatex')->setParameter('nomex','%'.$valeur.'%')->setParameter('cinex',$valeur)->setParameter('adresseex','%'.$valeur.'%')->setParameter('etatex',$valeur)->orderBy('e.nomex','ASC')->getQuery();

    return $query->getResult();
}
}