<?php
/**
 * Created by PhpStorm.
 * User: youssef
 * Date: 3/25/2019
 * Time: 2:21 PM
 */
// src/ReglementBundle/Controller/ReglementRepository.php
namespace ReglementBundle\Controller;
use Doctrine\ORM\EntityRepository;
use SinistreBundle\Entity\Sinistre;
use ReglementBundle\Entity\Reglment;

class ReglementRepository extends EntityRepository
{
    public function findCin($email)
    {
        $query = $this->getEntityManager()
            ->createQuery("SELECT c.cin from AppBundle:AssureParticulier c Where c.email=:email")->setParameter('email',$email);
        return $query->getResult();
    }

}