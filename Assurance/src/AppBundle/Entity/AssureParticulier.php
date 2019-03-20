<?php

namespace AppBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * AssureParticulier
 *
 * @ORM\Table(name="assure_particulier")
 * @ORM\Entity
 */
class AssureParticulier
{
    /**
     * @var integer
     *
     * @ORM\Column(name="cin", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $cin;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=20, nullable=true)
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="prenom", type="string", length=20, nullable=true)
     */
    private $prenom;

    /**
     * @var string
     *
     * @ORM\Column(name="email", type="string", length=50, nullable=true)
     */
    private $email;

    /**
     * @var integer
     *
     * @ORM\Column(name="numtel", type="integer", nullable=true)
     */
    private $numtel;

    /**
     * @var string
     *
     * @ORM\Column(name="adresse", type="string", length=20, nullable=true)
     */
    private $adresse;


}

