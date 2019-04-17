<?php
/**
 * Created by PhpStorm.
 * User: HP
 * Date: 25/03/2019
 * Time: 23:06
 */

namespace ReparateurBundle\Repository;


class ReparateurRepository extends \Doctrine\ORM\EntityRepository
{
    public function findReparateur($valeur)
    {

        $query=$this->createQueryBuilder('e')->where('e.nomrep like :nomrep or e.cinrep=:cinrep or e.adresserep like :adresserep or e.etatrep=:etatrep')->setParameter('nomrep','%'.$valeur.'%')->setParameter('cinrep',$valeur)->setParameter('adresserep','%'.$valeur.'%')->setParameter('etatrep',$valeur)->orderBy('e.nomrep','ASC')->getQuery();

        return $query->getResult();
    }

}