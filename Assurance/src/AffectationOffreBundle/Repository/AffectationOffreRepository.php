<?php
/**
 * Created by PhpStorm.
 * User: ADMIN
 * Date: 27/03/2019
 * Time: 17:11
 */

namespace AffectationOffreBundle\Repository;

/**
 * AffectationOffreRepository
 */
use Doctrine\ORM\EntityRepository;
class AffectationOffreRepository extends \Doctrine\ORM\EntityRepository
{
  public function assureExiste($assure)
  {
      $query=$this->getEntityManager()
          ->createQuery("select m.idassure as id from AffectationOffreBundle:Affectationoffre m")
          ->getResult();
      foreach ($query as $a)
      {
          if(strcmp($a["id"],$assure)==0)
              return true;
      }
      return false;
  }
  public function assureOffre($id)
  {
      return $query=$this->getEntityManager()
          ->createQuery("select m from AffectationOffreBundle:Affectationoffre m where m.idoffre=:id ")
          ->setParameter('id',$id)
          ->getResult();

  }
}