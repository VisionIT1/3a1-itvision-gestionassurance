<?php

namespace AppBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * LigneGarantie
 *
 * @ORM\Table(name="ligne_garantie")
 * @ORM\Entity
 */
class LigneGarantie
{
    /**
     * @var integer
     *
     * @ORM\Column(name="idgh", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idgh;

    /**
     * @var integer
     *
     * @ORM\Column(name="id_type", type="integer", nullable=false)
     */
    private $idType;

    /**
     * @var integer
     *
     * @ORM\Column(name="id_garantie", type="integer", nullable=false)
     */
    private $idGarantie;

    /**
     * @var float
     *
     * @ORM\Column(name="prime", type="float", precision=10, scale=0, nullable=false)
     */
    private $prime;


}

