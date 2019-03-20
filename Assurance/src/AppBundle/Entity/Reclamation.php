<?php

namespace AppBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Reclamation
 *
 * @ORM\Table(name="reclamation")
 * @ORM\Entity
 */
class Reclamation
{
    /**
     * @var integer
     *
     * @ORM\Column(name="idR", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idr;

    /**
     * @var string
     *
     * @ORM\Column(name="typeRec", type="string", length=100, nullable=false)
     */
    private $typerec;

    /**
     * @var string
     *
     * @ORM\Column(name="descRec", type="string", length=100, nullable=false)
     */
    private $descrec;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="dateSaisiRec", type="date", nullable=false)
     */
    private $datesaisirec;

    /**
     * @var string
     *
     * @ORM\Column(name="typeAssure", type="string", length=30, nullable=false)
     */
    private $typeassure;

    /**
     * @var string
     *
     * @ORM\Column(name="idCli", type="string", length=30, nullable=false)
     */
    private $idcli;

    /**
     * @var integer
     *
     * @ORM\Column(name="traitementRec", type="integer", nullable=false)
     */
    private $traitementrec = '0';


}

