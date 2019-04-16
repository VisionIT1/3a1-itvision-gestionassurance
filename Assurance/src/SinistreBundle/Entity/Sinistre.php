<?php

namespace SinistreBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Sinistre
 *
 * @ORM\Table(name="sinistre")
 * @ORM\Entity(repositoryClass="SinistreBundle\Controller\SinistreRepository")
 */
class Sinistre
{
    /**
     * @var integer
     *
     * @ORM\Column(name="code_sinistre", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $codeSinistre;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_declaration", type="date", nullable=true)
     */
    private $dateDeclaration;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_sinistre", type="date", nullable=true)
     */
    private $dateSinistre;

    /**
     * @var string
     *
     * @ORM\Column(name="lieu_sinistre", type="string", length=50, nullable=true)
     */
    private $lieuSinistre;

    /**
     * @var integer
     *
     * @ORM\Column(name="numero_sinistre", type="integer", nullable=true)
     */
    private $numeroSinistre;

    /**
     * @var integer
     *
     * @ORM\Column(name="dommage_mat", type="integer", nullable=true)
     */
    private $dommageMat;

    /**
     * @var integer
     *
     * @ORM\Column(name="dommage_corp", type="integer", nullable=true)
     */
    private $dommageCorp;

    /**
     * @var integer
     *
     * @ORM\Column(name="code_assureur", type="integer", nullable=true)
     */
    private $codeAssureur;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="text", length=65535, nullable=true)
     */
    private $description;


    /**
     * @var integer
     *
     * @ORM\Column(name="etat", type="integer", nullable=true)
     */
    private $etat;

    /**
     * @return int
     */
    public function getCodeSinistre()
    {
        return $this->codeSinistre;
    }

    /**
     * @param int $codeSinistre
     */
    public function setCodeSinistre($codeSinistre)
    {
        $this->codeSinistre = $codeSinistre;
    }

    /**
     * @return \DateTime
     */
    public function getDateDeclaration()
    {
        return $this->dateDeclaration;
    }

    /**
     * @param \DateTime $dateDeclaration
     */
    public function setDateDeclaration($dateDeclaration)
    {
        $this->dateDeclaration = $dateDeclaration;
    }

    /**
     * @return \DateTime
     */
    public function getDateSinistre()
    {
        return $this->dateSinistre;
    }

    /**
     * @param \DateTime $dateSinistre
     */
    public function setDateSinistre($dateSinistre)
    {
        $this->dateSinistre = $dateSinistre;
    }

    /**
     * @return string
     */
    public function getLieuSinistre()
    {
        return $this->lieuSinistre;
    }

    /**
     * @param string $lieuSinistre
     */
    public function setLieuSinistre($lieuSinistre)
    {
        $this->lieuSinistre = $lieuSinistre;
    }

    /**
     * @return int
     */
    public function getNumeroSinistre()
    {
        return $this->numeroSinistre;
    }

    /**
     * @param int $numeroSinistre
     */
    public function setNumeroSinistre($numeroSinistre)
    {
        $this->numeroSinistre = $numeroSinistre;
    }

    /**
     * @return int
     */
    public function getDommageMat()
    {
        return $this->dommageMat;
    }

    /**
     * @param int $dommageMat
     */
    public function setDommageMat($dommageMat)
    {
        $this->dommageMat = $dommageMat;
    }

    /**
     * @return int
     */
    public function getDommageCorp()
    {
        return $this->dommageCorp;
    }

    /**
     * @param int $dommageCorp
     */
    public function setDommageCorp($dommageCorp)
    {
        $this->dommageCorp = $dommageCorp;
    }

    /**
     * @return int
     */
    public function getCodeAssureur()
    {
        return $this->codeAssureur;
    }

    /**
     * @param int $codeAssureur
     */
    public function setCodeAssureur($codeAssureur)
    {
        $this->codeAssureur = $codeAssureur;
    }

    /**
     * @return string
     */
    public function getDescription()
    {
        return $this->description;
    }

    /**
     * @param string $description
     */
    public function setDescription($description)
    {
        $this->description = $description;
    }

    /**
     * @return int
     */
    public function getEtat()
    {
        return $this->etat;
    }

    /**
     * @param int $etat
     */
    public function setEtat($etat)
    {
        $this->etat = $etat;
    }



}

