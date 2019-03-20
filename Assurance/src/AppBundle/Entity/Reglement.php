<?php

namespace AppBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Reglement
 *
 * @ORM\Table(name="reglement")
 * @ORM\Entity
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


}

