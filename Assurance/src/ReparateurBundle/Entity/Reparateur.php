<?php
/**
 * Created by PhpStorm.
 * User: HP
 * Date: 25/03/2019
 * Time: 13:22
 */

namespace ReparateurBundle\Entity;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use Vich\UploaderBundle\Mapping\Annotation as Vich;
use Symfony\Component\HttpFoundation\File\File;


/**
 * @ORM\Table(name="reparateur")
 * @ORM\Entity(repositoryClass="ReparateurBundle\Repository\ReparateurRepository")
 * @UniqueEntity("mailrep")
 * @UniqueEntity("cinrep")
 * @UniqueEntity("numerorep")
 * @UniqueEntity("faxrep")
 *  @Vich\Uploadable()
 */

class Reparateur
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     * @ORM\Column(name="idRep",type="integer")
     */
    private $idrep;
    /**
     * @var File|null
     * @Vich\UploadableField(mapping="property_image" ,fileNameProperty="fileName")
     */
    private $imageFile;
    /**
     * @var integer
     *  @Assert\Length(
     *     min=7,
     *     max=10,
     *     exactMessage="verifier la longueur du cin")
     * @ORM\Column(name="cinRep",type="integer")
     */
    private $cinrep;
    /**
     * @var integer
     * @Assert\Length(
     *     min=8,
     *     max=12,
     *     exactMessage="verifier la longeur du fax")
     * @Assert\NotBlank(message="ce champs est obligatoire a remplir")
     * @ORM\Column(name="faxRep",type="integer")
     */
    private $faxrep;
    /**
     * @var string
     *  @Assert\Regex(
     *     pattern="/^[a-z]+$/i",
     * htmlPattern="^[a-zA-Z]+$",message="le nom est une chaine de caractere",
     * )
     * @Assert\NotBlank(message="ce champs est obligatoire a remplir")
     * @ORM\Column(name="nomRep",type="string",length=60)
     */
    private $nomrep;
    /**
     * @var string
     *  @Assert\Regex(
     *   pattern="/^[a-z]+$/i",
     * htmlPattern="^[a-zA-Z]+$",message="le prenom est une chaine de caractere",
     * )
     * @Assert\NotBlank(message="ce champs est obligatoire a remplir")
     * @ORM\Column(name="prenomRep",type="string",length=60)
     */
    private $prenomrep;
    /**
     * @var string
     * @Assert\NotBlank(message="ce champs est obligatoire a remplir")
     * @ORM\Column(name="mailrep",type="string",length=60)
     */
    private $mailrep;
    /**
     * @var integer
     * @Assert\Length(
     * min=8,
     * max=8,
     *exactMessage="verifier la longueur du telephone")
     * @Assert\NotBlank(message="ce champs est obligatoire a remplir")
     * @ORM\Column(name="numeroRep",type="integer")
     */
    private $numerorep;
    /**
     * @var string
     * @Assert\Regex(
     *     pattern="/^[a-z]+$/i",
     * htmlPattern="^[a-zA-Z]+$",message="l'adresse est une chaine de caractere",
     * )
     * @Assert\NotBlank(message="ce champs est obligatoire a remplir")
     * @ORM\Column(name="adresseRep",type="string",length=60)
     */
    private $adresserep;
    /**
     *  @Assert\NotBlank(message="ce champs est obligatoire a remplir")
     * @ORM\Column(name="etatRep",type="string",length=3)
     */

    private $etatrep;
    /**
     * @var string
     * @Assert\Regex(
     *     pattern="/^[a-z]+$/i",
     * htmlPattern="^[a-zA-Z]+$",message="la description est une chaine de caractere",
     * )
     * @Assert\NotBlank(message="ce champs est obligatoire a remplir")
     * @ORM\Column(name="descriptionRep",type="string",length=60)
     */
    private $descriptionrep;
    /**
     * @var string|null
     * @ORM\Column(name="fileName",type="string",length=100)
     */
    private $fileName;

    /**
     * @return mixed
     */
    public function getIdrep()
    {
        return $this->idrep;
    }

    /**
     * @param mixed $idrep
     */
    public function setIdrep($idrep)
    {
        $this->idrep = $idrep;
    }

    /**
     * @return int
     */
    public function getCinrep()
    {
        return $this->cinrep;
    }

    /**
     * @param int $cinrep
     */
    public function setCinrep($cinrep)
    {
        $this->cinrep = $cinrep;
    }

    /**
     * @return int
     */
    public function getFaxrep()
    {
        return $this->faxrep;
    }

    /**
     * @param int $faxrep
     */
    public function setFaxrep($faxrep)
    {
        $this->faxrep = $faxrep;
    }

    /**
     * @return string
     */
    public function getNomrep()
    {
        return $this->nomrep;
    }

    /**
     * @param string $nomrep
     */
    public function setNomrep($nomrep)
    {
        $this->nomrep = $nomrep;
    }

    /**
     * @return string
     */
    public function getPrenomrep()
    {
        return $this->prenomrep;
    }

    /**
     * @param string $prenomrep
     */
    public function setPrenomrep($prenomrep)
    {
        $this->prenomrep = $prenomrep;
    }

    /**
     * @return string
     */
    public function getMailrep()
    {
        return $this->mailrep;
    }

    /**
     * @param string $mailrep
     */
    public function setMailrep($mailrep)
    {
        $this->mailrep = $mailrep;
    }

    /**
     * @return int
     */
    public function getNumerorep()
    {
        return $this->numerorep;
    }

    /**
     * @param int $numerorep
     */
    public function setNumerorep($numerorep)
    {
        $this->numerorep = $numerorep;
    }

    /**
     * @return string
     */
    public function getAdresserep()
    {
        return $this->adresserep;
    }

    /**
     * @param string $adresserep
     */
    public function setAdresserep($adresserep)
    {
        $this->adresserep = $adresserep;
    }

    /**
     * @return mixed
     */
    public function getEtatrep()
    {
        return $this->etatrep;
    }

    /**
     * @param mixed $etatrep
     */
    public function setEtatrep($etatrep)
    {
        $this->etatrep = $etatrep;
    }

    /**
     * @return string
     */
    public function getDescriptionrep()
    {
        return $this->descriptionrep;
    }

    /**
     * @param string $descriptionrep
     */
    public function setDescriptionrep($descriptionrep)
    {
        $this->descriptionrep = $descriptionrep;
    }

    /**
     * @return File|null
     */
    public function getImageFile()
    {
        return $this->imageFile;
    }

    /**
     * @param File|null $imageFile
     */
    public function setImageFile($imageFile)
    {
        $this->imageFile = $imageFile;
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




}