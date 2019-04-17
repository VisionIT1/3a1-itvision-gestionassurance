<?php

namespace AffectationOffreBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Affectationoffre
 *
 * @ORM\Table(name="affectationoffre")
 * @ORM\Entity(repositoryClass="AffectationOffreBundle\Repository\AffectationOffreRepository")
 */
class Affectationoffre
{
    /**
     * @var integer
     *
     * @ORM\Column(name="idAff", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idaff;

    /**
     * @var integer
     *
     * @ORM\Column(name="idOffre", type="integer", nullable=false)
     */
    private $idoffre;

    /**
     * @var string
     *
     * @ORM\Column(name="idAssure", type="string", length=20, nullable=false)
     */
    private $idassure;

    /**
     * @var string
     *
     * @ORM\Column(name="typeAssure", type="string", length=30, nullable=false)
     */
    private $typeassure;

    /**
     * @return int
     */
    public function getIdaff()
    {
        return $this->idaff;
    }

    /**
     * @param int $idaff
     */
    public function setIdaff($idaff)
    {
        $this->idaff = $idaff;
    }

    /**
     * @return int
     */
    public function getIdoffre()
    {
        return $this->idoffre;
    }

    /**
     * @param int $idoffre
     */
    public function setIdoffre($idoffre)
    {
        $this->idoffre = $idoffre;
    }

    /**
     * @return string
     */
    public function getIdassure()
    {
        return $this->idassure;
    }

    /**
     * @param string $idassure
     */
    public function setIdassure($idassure)
    {
        $this->idassure = $idassure;
    }

    /**
     * @return string
     */
    public function getTypeassure()
    {
        return $this->typeassure;
    }

    /**
     * @param string $typeassure
     */
    public function setTypeassure($typeassure)
    {
        $this->typeassure = $typeassure;
    }


}

