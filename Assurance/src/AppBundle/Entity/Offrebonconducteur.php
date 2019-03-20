<?php

namespace AppBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Offrebonconducteur
 *
 * @ORM\Table(name="offrebonconducteur")
 * @ORM\Entity
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
     *
     * @ORM\Column(name="libOffre", type="string", length=300, nullable=false)
     */
    private $liboffre;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="dateDebutOffre", type="date", nullable=false)
     */
    private $datedebutoffre;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="dateFinOffre", type="date", nullable=false)
     */
    private $datefinoffre;

    /**
     * @var integer
     *
     * @ORM\Column(name="pourcentageReduction", type="integer", nullable=false)
     */
    private $pourcentagereduction;

    /**
     * @var integer
     *
     * @ORM\Column(name="numReglement", type="integer", nullable=false)
     */
    private $numreglement;

    /**
     * @var integer
     *
     * @ORM\Column(name="nbrAnnee", type="integer", nullable=false)
     */
    private $nbrannee;

    /**
     * @var string
     *
     * @ORM\Column(name="descripOffre", type="string", length=100, nullable=false)
     */
    private $descripoffre;


}

