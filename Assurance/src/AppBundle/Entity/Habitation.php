<?php

namespace AppBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Habitation
 *
 * @ORM\Table(name="habitation")
 * @ORM\Entity
 */
class Habitation
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id_habitat", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idHabitat;

    /**
     * @var integer
     *
     * @ORM\Column(name="baieVitre", type="integer", nullable=false)
     */
    private $baievitre;

    /**
     * @var integer
     *
     * @ORM\Column(name="nbPieces", type="integer", nullable=false)
     */
    private $nbpieces;

    /**
     * @var float
     *
     * @ORM\Column(name="valmobile", type="float", precision=10, scale=0, nullable=false)
     */
    private $valmobile;

    /**
     * @var integer
     *
     * @ORM\Column(name="sysAlarm", type="integer", nullable=false)
     */
    private $sysalarm;

    /**
     * @var string
     *
     * @ORM\Column(name="natureLocal", type="string", length=100, nullable=false)
     */
    private $naturelocal;


}

