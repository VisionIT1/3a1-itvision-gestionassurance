<?php

namespace AppBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Marque
 *
 * @ORM\Table(name="marque")
 * @ORM\Entity
 */
class Marque
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id_marque", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idMarque;

    /**
     * @var string
     *
     * @ORM\Column(name="libelle", type="string", length=500, nullable=false)
     */
    private $libelle;


}

