<?php

namespace OffreFideliteBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Offrefidelite
 *
 * @ORM\Table(name="offrefidelite")
 * @ORM\Entity(repositoryClass="OffreFideliteBundle\Repository\OffreFideliteRepository")
 */
class Offrefidelite
{
    /**
     * @var integer
     *
     * @ORM\Column(name="idOffre", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idoffre;

    /**
     * @var string
     * @Assert\Regex(
     *     pattern     = "/^[a-z]+$/i",
     *     htmlPattern = "^[a-zA-Z]+$",message="Le libelle est une chaine de caractére",
     * )
     * @ORM\Column(name="libOffre", type="string", length=30, nullable=false)
     */
    private $liboffre;

    /**
     * @var \DateTime
     * @Assert\Type("DateTime")
     * @Assert\GreaterThanOrEqual(
     *      value = "today",
     *      message = "La date debut doit être supérieur ou egal à la date d'aujourd'hui"
     * )
     * @ORM\Column(name="dateDebutOffre", type="date", nullable=false)
     */
    private $datedebutoffre;

    /**
     * @var \DateTime
     * @Assert\Type("DateTime")
     * @Assert\Expression("value >= this.getDateDebutOffre()",message="La date fin vient après la date début")
     * @ORM\Column(name="dateFinOffre", type="date", nullable=false)
     */
    private $datefinoffre;

    /**
     * @var integer
     * @ORM\Column(name="pourcentageReduction", type="integer", nullable=false)
     * @Assert\Range(
     *      min = 0,
     *      max = 100,
     *      minMessage = "le pourcentage minimale est de  {{ limit }} %",
     *      maxMessage = "le pourcentage maximal est de  {{ limit }} %"
     * )
     * @ORM\Column(name="pourcentageReduction", type="integer", nullable=false)
     */
    private $pourcentagereduction;

    /**
     * @var integer
     * @Assert\Range(
     *      min = 3,
     *      minMessage = "le nbr d'annee minimale est de  {{ limit }} "
     * )
     * @ORM\Column(name="nbrContratMin", type="integer", nullable=false)
     */
    private $nbrcontratmin;

    /**
     * @var string
     * @Assert\Length(
     *      min = 10,
     *      minMessage = "la description doit contenir au moins {{ limit }} caractéres")
     * @ORM\Column(name="descripOffre", type="string", length=100, nullable=false)
     */
    private $descripoffre;
    /**
     * @var string
     * @Assert\Length(
     *      min = 10,
     *      minMessage = "l'image doit contenir au moins {{ limit }} caractéres")
     * @ORM\Column(name="imgOff", type="string", length=100, nullable=true)
     */
    private $imgOff;

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
    public function getLiboffre()
    {
        return $this->liboffre;
    }

    /**
     * @param string $liboffre
     */
    public function setLiboffre($liboffre)
    {
        $this->liboffre = $liboffre;
    }

    /**
     * @return \DateTime
     */
    public function getDatedebutoffre()
    {
        return $this->datedebutoffre;
    }

    /**
     * @param \DateTime $datedebutoffre
     */
    public function setDatedebutoffre($datedebutoffre)
    {
        $this->datedebutoffre = $datedebutoffre;
    }

    /**
     * @return \DateTime
     */
    public function getDatefinoffre()
    {
        return $this->datefinoffre;
    }

    /**
     * @param \DateTime $datefinoffre
     */
    public function setDatefinoffre($datefinoffre)
    {
        $this->datefinoffre = $datefinoffre;
    }

    /**
     * @return int
     */
    public function getPourcentagereduction()
    {
        return $this->pourcentagereduction;
    }

    /**
     * @param int $pourcentagereduction
     */
    public function setPourcentagereduction($pourcentagereduction)
    {
        $this->pourcentagereduction = $pourcentagereduction;
    }

    /**
     * @return int
     */
    public function getNbrcontratmin()
    {
        return $this->nbrcontratmin;
    }

    /**
     * @param int $nbrcontratmin
     */
    public function setNbrcontratmin($nbrcontratmin)
    {
        $this->nbrcontratmin = $nbrcontratmin;
    }

    /**
     * @return string
     */
    public function getDescripoffre()
    {
        return $this->descripoffre;
    }

    /**
     * @param string $descripoffre
     */
    public function setDescripoffre($descripoffre)
    {
        $this->descripoffre = $descripoffre;
    }

    /**
     * @return string
     */
    public function getImgOff()
    {
        return $this->imgOff;
    }

    /**
     * @param string $imgOff
     */
    public function setImgOff($imgOff)
    {
        $this->imgOff = $imgOff;
    }



}

