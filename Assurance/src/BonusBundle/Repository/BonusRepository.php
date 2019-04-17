<?php
/**
 * Created by PhpStorm.
 * User: HP
 * Date: 29/03/2019
 * Time: 23:03
 */

namespace BonusBundle\Repository;




use AppBundle\Entity\Contrat;

class BonusRepository extends \Doctrine\ORM\EntityRepository
{

    public function findContratPourBonus()
    {


        //$query=$this->createQueryBuilder('e')->where('extract(day from e.date_debut)=extract(day from sysdate()) ')->getQuery();

    $em=$this->getEntityManager();
    $query=$em->createQueryBuilder()->select('c')->from('ContratBundle:Contrat' ,'c')->where('c.dateDebut=current_date()')->getQuery();

        return $query->getResult();

    }
   /* public function findContratById($id)
    {



        $em=$this->getEntityManager();
        $query=$em->createQueryBuilder()->select('c.id','c.cinAssure')->from('ContratBundle:Contrat' ,'c')->where(' c.id=:id')->setParameter('id',$id)->getQuery();

        return $query->getResult();

    }*/
    public function findBonusDuContratEcheable()
    {

        $em=$this->getEntityManager();
        $query=$em->createQueryBuilder()->select('b')->from('BonusBundle:Bonus' ,'b')->join('ContratBundle:Contrat','c')->where('c.dateEcheance>current_date() AND c.id=b.id_contrat and b.score=1')->getQuery();
        return $query->getResult();

    }
public function calculerScorePourBonus($id_bonus)
{

    $em=$this->getEntityManager();
    $query=$em->createQueryBuilder()->select('count(s)')->from('SinistreBundle:Sinistre','s')->join('BonusBundle:Bonus','b')->where('s.codeAssureur=b.id_assureur AND b.id_bonus=:id_bonus')->setParameter('id_bonus',$id_bonus)->getQuery();
     //$query=$this->getEntityManager()->createQuery('select count(s) from SinistreBundle:Sinistre s ,BonusBundle:Bonus b where s.codeAssureur=:b.id_assureur AND b.id_bonus=:id_bonus')->setParameter('id_bonus',$id_bonus);
    return $query->getResult();
}
    public function afficherBonusFront($cin)
    {
       // $q=$this->getEntityManager()->createQuery("select b from BonusBundle:Bonus b where b.id_bonus =:id_bonus")->setParameter('id_bonus',$id_bonus);
       // return $q->getResult();
        $q=$this->getEntityManager()->createQuery("select b from BonusBundle:Bonus b where b.id_assureur=:cin")->setParameter('cin',$cin);
        return $q->getResult();

    }
 public function getBonus()
    {
        return $query=$this->getEntityManager()
            ->createQuery("select b from BonusBundle:Bonus b where b.etat=1")
            ->getResult();
    }

    public function supprimerBonus($idcontrat){
        $q=$this->getEntityManager()->createQuery("delete from BonusBundle:Bonus b where b.id_contrat=:idcontrat")->setParameter('idcontrat',$idcontrat);
        return $q->getResult();

    }

}