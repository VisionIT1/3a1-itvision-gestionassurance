<?php

namespace AppBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Affectationoffre
 *
 * @ORM\Table(name="affectationoffre")
 * @ORM\Entity
 */
class Affectationoffre
{
    /**
     * @var integer
     *
     * @ORM\Column(name="idAff", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idaff;

    /**
     * @var integer
     *
     * @ORM\Column(name="idOffre", type="integer", nullable=false)
     */
    private $idoffre;

    /**
     * @var string
     *
     * @ORM\Column(name="idAssure", type="string", length=20, nullable=false)
     */
    private $idassure;

    /**
     * @var string
     *
     * @ORM\Column(name="typeAssure", type="string", length=30, nullable=false)
     */
    private $typeassure;


}

