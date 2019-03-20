<?php

namespace AppBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Garantie
 *
 * @ORM\Table(name="garantie")
 * @ORM\Entity
 */
class Garantie
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id_garantie", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idGarantie;

    /**
     * @var string
     *
     * @ORM\Column(name="lib", type="string", length=500, nullable=false)
     */
    private $lib;

    /**
     * @var float
     *
     * @ORM\Column(name="prime", type="float", precision=10, scale=0, nullable=false)
     */
    private $prime;

    /**
     * @var string
     *
     * @ORM\Column(name="categorie", type="string", length=100, nullable=false)
     */
    private $categorie;


}

