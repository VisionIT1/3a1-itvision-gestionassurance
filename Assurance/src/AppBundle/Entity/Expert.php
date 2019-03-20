<?php

namespace AppBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Expert
 *
 * @ORM\Table(name="expert", uniqueConstraints={@ORM\UniqueConstraint(name="ceu", columns={"cinEX"})})
 * @ORM\Entity
 */
class Expert
{
    /**
     * @var integer
     *
     * @ORM\Column(name="idEx", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idex;

    /**
     * @var integer
     *
     * @ORM\Column(name="cinEX", type="integer", nullable=false)
     */
    private $cinex;

    /**
     * @var integer
     *
     * @ORM\Column(name="faxEx", type="integer", nullable=false)
     */
    private $faxex;

    /**
     * @var string
     *
     * @ORM\Column(name="nomEx", type="string", length=20, nullable=false)
     */
    private $nomex;

    /**
     * @var string
     *
     * @ORM\Column(name="prenomEx", type="string", length=20, nullable=false)
     */
    private $prenomex;

    /**
     * @var string
     *
     * @ORM\Column(name="mailEx", type="string", length=60, nullable=true)
     */
    private $mailex;

    /**
     * @var integer
     *
     * @ORM\Column(name="numeroEx", type="integer", nullable=false)
     */
    private $numeroex;

    /**
     * @var string
     *
     * @ORM\Column(name="adresseEx", type="string", length=100, nullable=false)
     */
    private $adresseex;

    /**
     * @var string
     *
     * @ORM\Column(name="etatEx", type="string", length=20, nullable=false)
     */
    private $etatex;

    /**
     * @var string
     *
     * @ORM\Column(name="descriptionEx", type="string", length=20, nullable=false)
     */
    private $descriptionex;


}

