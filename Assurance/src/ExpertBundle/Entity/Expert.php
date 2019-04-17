<?php
/**
 * Created by PhpStorm.
 * User: HP
 * Date: 22/03/2019
 * Time: 20:04
 */

namespace ExpertBundle\Entity;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\HttpFoundation\File\File;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use Vich\UploaderBundle\Mapping\Annotation as Vich;
/**
 * @ORM\Table(name="expert")
 * @ORM\Entity(repositoryClass="ExpertBundle\Repository\ExpertRepository")
 * @UniqueEntity("mailex")
 * @UniqueEntity("cinex")
 * @UniqueEntity("numeroex")
 * @UniqueEntity("faxex")
 * @Vich\Uploadable()
 */

class Expert
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     * @ORM\Column(name="idEx",type="integer")
     */
    private $idex;
    /**
     * @var File|null
     * @Vich\UploadableField(mapping="property_image" ,fileNameProperty="fileName")
     */
    private $imageFile;
    /**
     * @var integer
     * @Assert\Length(
     *     min=7,
     *     max=10,
     *     exactMessage="verifier la longueur du cin")
     * @Assert\NotBlank(message="ce champs est obligatoire a remplir")
     * @ORM\Column(name="cinEX",type="integer")
     */
    private $cinex;
    /**
     * @var integer
     * @Assert\Length(
     *     min=8,
     *     max=12,
     *     exactMessage="verifier la longeur du fax")
     * @Assert\NotBlank(message="ce champs est obligatoire a remplir")
     * @ORM\Column(name="faxEx",type="integer")
     */
    private $faxex;
    /**
     * @var string
     *  @Assert\Regex(
     *     pattern="/^[a-z]+$/i",
     * htmlPattern="^[a-zA-Z]+$",message="le nom est une chaine de caractere",
     * )
     * @Assert\NotBlank(message="ce champs est obligatoire a remplir")
     * @ORM\Column(name="nomEx",type="string",length=60)
     */
    private $nomex;
    /**
     * @var string
     *   @Assert\Regex(
     *   pattern="/^[a-z]+$/i",
     * htmlPattern="^[a-zA-Z]+$",message="le prenom est une chaine de caractere",
     * )
     * @Assert\NotBlank(message="ce champs est obligatoire a remplir")
     * @ORM\Column(name="prenomEx",type="string",length=60)
     */
    private $prenomex;
    /**
     * @var string
     * @Assert\NotBlank(message="ce champs est obligatoire a remplir")
     * @ORM\Column(name="mailEx",type="string",length=60)
     */
    private $mailex;
    /**
     * @var integer
     * @Assert\Length(
     * min=8,
     * max=8,
     *exactMessage="verifier la longueur du telephone")
     * @Assert\NotBlank(message="ce champs est obligatoire a remplir")
     * @ORM\Column(name="numeroEx",type="integer")
     */
    private $numeroex;
    /**
     * @var string
     * @Assert\Regex(
     *     pattern="/^[a-z]+$/i",
     * htmlPattern="^[a-zA-Z]+$",message="l'adresse est une chaine de caractere",
     * )
     * @Assert\NotBlank(message="ce champs est obligatoire a remplir")
     * @ORM\Column(name="adresseEx",type="string",length=60)
     */
    private $adresseex;
    /**
     * @Assert\NotBlank(message="ce champs est obligatoire a remplir")
     * @ORM\Column(name="etatEx",type="string",length=3)
     */

    private $etatex;
    /**
     * @var string
     *   @Assert\Regex(
     *     pattern="/^[a-z]+$/i",
     * htmlPattern="^[a-zA-Z]+$",message="la description est une chaine de caractere",
     * )
     * @ORM\Column(name="descriptionEx",type="string",length=60)
     */
    private $descriptionex;
    /**
     * @var string|null
     * @ORM\Column(name="fileName",type="string",length=100)
     */
    private $fileName;

    /**
     * @return mixed
     */
    public function getIdex()
    {
        return $this->idex;
    }

    /**
     * @param mixed $idex
     */
    public function setIdex($idex)
    {
        $this->idex = $idex;
    }

    /**
     * @return mixed
     */
    public function getCinex()
    {
        return $this->cinex;
    }

    /**
     * @param mixed $cinex
     */
    public function setCinex($cinex)
    {
        $this->cinex = $cinex;
    }

    /**
     * @return mixed
     */
    public function getFaxex()
    {
        return $this->faxex;
    }

    /**
     * @param mixed $faxex
     */
    public function setFaxex($faxex)
    {
        $this->faxex = $faxex;
    }

    /**
     * @return mixed
     */
    public function getNomex()
    {
        return $this->nomex;
    }

    /**
     * @param mixed $nomex
     */
    public function setNomex($nomex)
    {
        $this->nomex = $nomex;
    }

    /**
     * @return mixed
     */
    public function getPrenomex()
    {
        return $this->prenomex;
    }

    /**
     * @param mixed $prenomex
     */
    public function setPrenomex($prenomex)
    {
        $this->prenomex = $prenomex;
    }

    /**
     * @return mixed
     */
    public function getMailex()
    {
        return $this->mailex;
    }

    /**
     * @param mixed $mailex
     */
    public function setMailex($mailex)
    {
        $this->mailex = $mailex;
    }

    /**
     * @return mixed
     */
    public function getNumeroex()
    {
        return $this->numeroex;
    }

    /**
     * @param mixed $numero
     */
    public function setNumeroex($numeroex)
    {
        $this->numeroex = $numeroex;
    }

    /**
     * @return mixed
     */
    public function getAdresseex()
    {
        return $this->adresseex;
    }

    /**
     * @param mixed $adresseex
     */
    public function setAdresseex($adresseex)
    {
        $this->adresseex = $adresseex;
    }

    /**
     * @return mixed
     */
    public function getEtatex()
    {
        return $this->etatex;
    }

    /**
     * @param mixed $etatex
     */
    public function setEtatex($etatex)
    {
        $this->etatex = $etatex;
    }

    /**
     * @return mixed
     */
    public function getDescriptionex()
    {
        return $this->descriptionex;
    }

    /**
     * @param mixed $descriptionex
     */
    public function setDescriptionex($descriptionex)
    {
        $this->descriptionex = $descriptionex;
    }

    /**
     * @return File|null
     */
    public function getImageFile()
    {
        return $this->imageFile;
    }



    /**
     * @return string|null
     */
    public function getFileName()
    {
        return $this->fileName;
    }

    /**
     * @param string|null $fileName
     */
    public function setFileName($fileName)
    {
        $this->fileName = $fileName;
    }

    /**
     * @param File|null $imageFile
     */
    public function setImageFile($imageFile)
    {
        $this->imageFile = $imageFile;
    }










}