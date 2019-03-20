<?php

namespace AppBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Voyage
 *
 * @ORM\Table(name="voyage")
 * @ORM\Entity
 */
class Voyage
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id_voyage", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idVoyage;

    /**
     * @var string
     *
     * @ORM\Column(name="destination", type="string", length=100, nullable=false)
     */
    private $destination;

    /**
     * @var integer
     *
     * @ORM\Column(name="dureeSejour", type="integer", nullable=false)
     */
    private $dureesejour;

    /**
     * @var integer
     *
     * @ORM\Column(name="trancheAge", type="integer", nullable=false)
     */
    private $trancheage;


}

