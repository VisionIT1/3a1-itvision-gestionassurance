<?php

namespace AppBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Vehicule
 *
 * @ORM\Table(name="vehicule")
 * @ORM\Entity
 */
class Vehicule
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id_vehicule", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idVehicule;

    /**
     * @var integer
     *
     * @ORM\Column(name="puissance", type="integer", nullable=false)
     */
    private $puissance;

    /**
     * @var float
     *
     * @ORM\Column(name="valNeuf", type="float", precision=10, scale=0, nullable=false)
     */
    private $valneuf;

    /**
     * @var float
     *
     * @ORM\Column(name="valVenale", type="float", precision=10, scale=0, nullable=false)
     */
    private $valvenale;

    /**
     * @var integer
     *
     * @ORM\Column(name="anneConst", type="integer", nullable=false)
     */
    private $anneconst;

    /**
     * @var integer
     *
     * @ORM\Column(name="id_marque", type="integer", nullable=false)
     */
    private $idMarque;

    /**
     * @var string
     *
     * @ORM\Column(name="immat", type="string", length=500, nullable=false)
     */
    private $immat;

    /**
     * @var integer
     *
     * @ORM\Column(name="id_garantie", type="integer", nullable=false)
     */
    private $idGarantie;


}

