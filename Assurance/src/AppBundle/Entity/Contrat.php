<?php

namespace AppBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Contrat
 *
 * @ORM\Table(name="contrat")
 * @ORM\Entity
 */
class Contrat
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=30, nullable=false)
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="text", length=65535, nullable=true)
     */
    private $description;

    /**
     * @var integer
     *
     * @ORM\Column(name="cin_assure", type="integer", nullable=true)
     */
    private $cinAssure;

    /**
     * @var string
     *
     * @ORM\Column(name="nomEntr", type="string", length=20, nullable=true)
     */
    private $nomentr;

    /**
     * @var string
     *
     * @ORM\Column(name="type", type="string", length=255, nullable=false)
     */
    private $type;

    /**
     * @var integer
     *
     * @ORM\Column(name="id_type", type="integer", nullable=false)
     */
    private $idType;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_debut", type="datetime", nullable=true)
     */
    private $dateDebut;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_echeance", type="datetime", nullable=true)
     */
    private $dateEcheance;

    /**
     * @var integer
     *
     * @ORM\Column(name="etat", type="integer", nullable=false)
     */
    private $etat;

    /**
     * @var float
     *
     * @ORM\Column(name="prime", type="float", precision=10, scale=0, nullable=false)
     */
    private $prime;

    /**
     * @var integer
     *
     * @ORM\Column(name="nvprime", type="integer", nullable=true)
     */
    private $nvprime;


}

