<?php

namespace AppBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Reparateur
 *
 * @ORM\Table(name="reparateur", uniqueConstraints={@ORM\UniqueConstraint(name="ceu", columns={"cinRep"})})
 * @ORM\Entity
 */
class Reparateur
{
    /**
     * @var integer
     *
     * @ORM\Column(name="idRep", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idrep;

    /**
     * @var integer
     *
     * @ORM\Column(name="cinRep", type="integer", nullable=false)
     */
    private $cinrep;

    /**
     * @var integer
     *
     * @ORM\Column(name="faxRep", type="integer", nullable=false)
     */
    private $faxrep;

    /**
     * @var string
     *
     * @ORM\Column(name="nomRep", type="string", length=20, nullable=false)
     */
    private $nomrep;

    /**
     * @var string
     *
     * @ORM\Column(name="prenomRep", type="string", length=20, nullable=false)
     */
    private $prenomrep;

    /**
     * @var string
     *
     * @ORM\Column(name="mailrep", type="string", length=60, nullable=true)
     */
    private $mailrep;

    /**
     * @var integer
     *
     * @ORM\Column(name="numeroRep", type="integer", nullable=false)
     */
    private $numerorep;

    /**
     * @var string
     *
     * @ORM\Column(name="adresseRep", type="string", length=100, nullable=false)
     */
    private $adresserep;

    /**
     * @var string
     *
     * @ORM\Column(name="etatRep", type="string", length=20, nullable=false)
     */
    private $etatrep;

    /**
     * @var string
     *
     * @ORM\Column(name="descriptionRep", type="string", length=20, nullable=false)
     */
    private $descriptionrep;


}

