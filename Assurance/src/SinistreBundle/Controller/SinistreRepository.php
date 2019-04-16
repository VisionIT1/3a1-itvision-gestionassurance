<?php
/**
 * Created by PhpStorm.
 * User: youssef
 * Date: 3/25/2019
 * Time: 2:21 PM
 */
// src/SinistreBundle/Controller/SinistreRepository.php
namespace SinistreBundle\Controller;
use Doctrine\ORM\EntityRepository;
use SinistreBundle\Entity\Sinistre;
use ReglementBundle\Entity\Reglment;

class SinistreRepository extends EntityRepository
{
    public function findCin($email)
    {



       $query = $this->getEntityManager()
           ->createQuery("SELECT c.cin from AppBundle:AssureParticulier c Where c.email=:email")->setParameter('email',$email);
        return $query->getResult();
    }

    public function findSin($email)
    {
        $query = $this->getEntityManager()
            ->createQuery("SELECT c.codeSinistre ,c.dateDeclaration, c.dateSinistre ,c.lieuSinistre ,c.dommageCorp ,c.dommageMat ,c.description from AppBundle:Sinistre c Where c.codeSinistre=:email")->setParameter('email',$email);
        return $query->getResult();
    }

    public function findCountAllByCin($email)
    {
        $m=$this->findCin($email);
        $query = $this->getEntityManager()
            ->createQuery("SELECT count(c) from AppBundle:Sinistre c Where c.codeAssureur=:codeAssureur")->setParameter('codeAssureur',$m[0]["cin"]);
        return $query->getResult();
    }

    public function findCountAll()
    {
        $query = $this->getEntityManager()
            ->createQuery("SELECT count(c) from AppBundle:Sinistre c Where c.etat=:etat")->setParameter('etat',0);
        return $query->getResult();
    }
    public function del($id)
    {

        $query = $this->getEntityManager()
            ->createQuery("DELETE  from AppBundle:Reglement c Where c.codeSin=:id")->setParameter('id',$id);
        return $query->getResult();
    }
}