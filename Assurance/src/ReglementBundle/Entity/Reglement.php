<?php

namespace ReglementBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Reglement
 *
 * @ORM\Table(name="reglement")
 * @ORM\Entity(repositoryClass="ReglementBundle\Controller\ReglementRepository")
 */
class Reglement
{
    /**
     * @var integer
     *
     * @ORM\Column(name="Code_regl", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $codeRegl;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="Date_regl", type="date", nullable=false)
     */
    private $dateRegl;

    /**
     * @var integer
     *
     * @ORM\Column(name="Frais_expert", type="integer", nullable=false)
     */
    private $fraisExpert;

    /**
     * @var string
     *
     * @ORM\Column(name="Mode_regl", type="string", length=30, nullable=false)
     */
    private $modeRegl;

    /**
     * @var integer
     *
     * @ORM\Column(name="Montant_regl", type="integer", nullable=false)
     */
    private $montantRegl;

    /**
     * @var integer
     *
     * @ORM\Column(name="cinAssureur", type="integer", nullable=false)
     */
    private $cinassureur;

    /**
     * @var integer
     *
     * @ORM\Column(name="idRep", type="integer", nullable=false)
     */
    private $idrep;

    /**
     * @var integer
     *
     * @ORM\Column(name="code_sin", type="integer", nullable=false)
     */
    private $codeSin;

    /**
     * @var integer
     *
     * @ORM\Column(name="idEx", type="integer", nullable=false)
     */
    private $idex;

    /**
     * @return int
     */
    public function getCodeRegl()
    {
        return $this->codeRegl;
    }

    /**
     * @param int $codeRegl
     */
    public function setCodeRegl($codeRegl)
    {
        $this->codeRegl = $codeRegl;
    }

    /**
     * @return \DateTime
     */
    public function getDateRegl()
    {
        return $this->dateRegl;
    }

    /**
     * @param \DateTime $dateRegl
     */
    public function setDateRegl($dateRegl)
    {
        $this->dateRegl = $dateRegl;
    }

    /**
     * @return int
     */
    public function getFraisExpert()
    {
        return $this->fraisExpert;
    }

    /**
     * @param int $fraisExpert
     */
    public function setFraisExpert($fraisExpert)
    {
        $this->fraisExpert = $fraisExpert;
    }

    /**
     * @return string
     */
    public function getModeRegl()
    {
        return $this->modeRegl;
    }

    /**
     * @param string $modeRegl
     */
    public function setModeRegl($modeRegl)
    {
        $this->modeRegl = $modeRegl;
    }

    /**
     * @return int
     */
    public function getMontantRegl()
    {
        return $this->montantRegl;
    }

    /**
     * @param int $montantRegl
     */
    public function setMontantRegl($montantRegl)
    {
        $this->montantRegl = $montantRegl;
    }

    /**
     * @return int
     */
    public function getCinassureur()
    {
        return $this->cinassureur;
    }

    /**
     * @param int $cinassureur
     */
    public function setCinassureur($cinassureur)
    {
        $this->cinassureur = $cinassureur;
    }

    /**
     * @return int
     */
    public function getIdrep()
    {
        return $this->idrep;
    }

    /**
     * @param int $idrep
     */
    public function setIdrep($idrep)
    {
        $this->idrep = $idrep;
    }

    /**
     * @return int
     */
    public function getCodeSin()
    {
        return $this->codeSin;
    }

    /**
     * @param int $codeSin
     */
    public function setCodeSin($codeSin)
    {
        $this->codeSin = $codeSin;
    }

    /**
     * @return int
     */
    public function getIdex()
    {
        return $this->idex;
    }

    /**
     * @param int $idex
     */
    public function setIdex($idex)
    {
        $this->idex = $idex;
    }


}

