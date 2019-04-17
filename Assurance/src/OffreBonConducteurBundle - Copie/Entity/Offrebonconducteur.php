<?php

namespace OffreBonConducteurBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
/**
 * Offrebonconducteur
 *
 * @ORM\Table(name="offrebonconducteur")
 * @ORM\Entity(repositoryClass="OffreBonConducteurBundle\Repository\OffreBonConducteurRepository")
 */
class Offrebonconducteur
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
     * @ORM\Column(name="libOffre", type="string", length=300, nullable=false)
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
    private $dateDebutOffre;

    /**
     * @var \DateTime
     * @Assert\Type("DateTime")
     * @Assert\Expression("value >= this.getDateDebutOffre()",message="La date fin vient après la date début")
     * @ORM\Column(name="dateFinOffre", type="date", nullable=false)
     */
    private $dateFinOffre;

    /**
     * @var integer
     * @ORM\Column(name="pourcentageReduction", type="integer", nullable=false)
     * @Assert\Range(
     *      min = 0,
     *      max = 100,
     *      minMessage = "le pourcentage minimale est de  {{ limit }} %",
     *      maxMessage = "le pourcentage maximal est de  {{ limit }} %"
     * )
     */
    private $pourcentagereduction;

    /**
     * @var integer
     * @ORM\Column(name="numReglement", type="integer", nullable=false)
     * @Assert\Range(
     *      min = 0,
     *      max = 5,
     *      minMessage = "le numero de réglement minimale est de  {{ limit }} ",
     *      maxMessage = "le numero de réglement maximal est de  {{ limit }} "
     * )
     */

    private $numreglement;

    /**
     * @var integer
     *
     * @ORM\Column(name="nbrAnnee", type="integer", nullable=false)
     * @Assert\Range(
     *      min = 1,
     *      max = 10,
     *      minMessage = "le nbr d'annee minimale est de  {{ limit }} ",
     *      maxMessage = "le nbr d'annee maximal est de  {{ limit }} "
     * )
     */
    private $nbrannee;

    /**
     * @var string
     *
     * @ORM\Column(name="descripOffre", type="string", length=100, nullable=false)
     * @Assert\Length(
     *      min = 10,
     *      minMessage = "la description doit contenir au moins {{ limit }} caractéres")
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
    public function getDateDebutOffre()
    {
        return $this->dateDebutOffre;
    }

    /**
     * @param \DateTime $dateDebutOffre
     */
    public function setDateDebutOffre($dateDebutOffre)
    {
        $this->dateDebutOffre = $dateDebutOffre;
    }

    /**
     * @return \DateTime
     */
    public function getDateFinOffre()
    {
        return $this->dateFinOffre;
    }

    /**
     * @param \DateTime $dateFinOffre
     */
    public function setDateFinOffre($dateFinOffre)
    {
        $this->dateFinOffre = $dateFinOffre;
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
    public function getNumreglement()
    {
        return $this->numreglement;
    }

    /**
     * @param int $numreglement
     */
    public function setNumreglement($numreglement)
    {
        $this->numreglement = $numreglement;
    }

    /**
     * @return int
     */
    public function getNbrannee()
    {
        return $this->nbrannee;
    }

    /**
     * @param int $nbrannee
     */
    public function setNbrannee($nbrannee)
    {
        $this->nbrannee = $nbrannee;
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

