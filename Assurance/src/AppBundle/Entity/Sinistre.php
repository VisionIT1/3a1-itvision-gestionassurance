<?php

namespace AppBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Sinistre
 *
 * @ORM\Table(name="sinistre")
 * @ORM\Entity
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


}

