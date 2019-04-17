<?php
/**
 * Created by PhpStorm.
 * User: HP
 * Date: 28/03/2019
 * Time: 00:18
 */

namespace BonusBundle\Entity;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Table(name="bonus")
 * @ORM\Entity(repositoryClass="BonusBundle\Repository\BonusRepository")
 */


class Bonus
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     * @ORM\Column(name="id_bonus",type="integer")
     */
    private $id_bonus;
    /**
     * @ORM\Column(name="id_contrat",type="integer")
     * @ORM\OneToOne(targetEntity="AppBundle\Entity\Contrat")
     * @ORM\JoinColumn(nullable=false)
     */
    private $id_contrat;

    /**
     * @ORM\Column(name="id_assureur",type="integer")
     * @ORM\OneToOne(targetEntity="AppBundle\Entity\AssureParticulier")
     * @ORM\JoinColumn(nullable=false)
     */
    private $id_assureur;
    /**
     * @var
     *   @ORM\Column(name="score",type="float",options={"default":1})
     */
    private  $score=1;
    /**
     * @ORM\Column(name="etat",type="integer")
     */
    private $etat=1;

    /**
     * @return mixed
     */
    public function getIdBonus()
    {
        return $this->id_bonus;
    }

    /**
     * @param mixed $id_bonus
     */
    public function setIdBonus($id_bonus)
    {
        $this->id_bonus = $id_bonus;
    }

    /**
     * @return mixed
     */
    public function getIdContrat()
    {
        return $this->id_contrat;
    }

    /**
     * @param mixed $id_contrat
     */
    public function setIdContrat($id_contrat)
    {
        $this->id_contrat = $id_contrat;
    }

    /**
     * @return mixed
     */
    public function getIdAssureur()
    {
        return $this->id_assureur;
    }

    /**
     * @param mixed $id_assureur
     */
    public function setIdAssureur($id_assureur)
    {
        $this->id_assureur = $id_assureur;
    }

    /**
     * @return mixed
     */
    public function getScore()
    {
        return $this->score;
    }

    /**
     * @param mixed $score
     */
    public function setScore($score)
    {
        $this->score = $score;
    }

    /**
     * @return mixed
     */
    public function getEtat()
    {
        return $this->etat;
    }

    /**
     * @param mixed $etat
     */
    public function setEtat($etat)
    {
        $this->etat = $etat;
    }


}