<?php

namespace AppBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * AssureEntreprise
 *
 * @ORM\Table(name="assure_entreprise")
 * @ORM\Entity
 */
class AssureEntreprise
{
    /**
     * @var string
     *
     * @ORM\Column(name="nomEntr", type="string", length=20, nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $nomentr;

    /**
     * @var string
     *
     * @ORM\Column(name="emailEntr", type="string", length=50, nullable=true)
     */
    private $emailentr;

    /**
     * @var string
     *
     * @ORM\Column(name="numtelEntr", type="string", length=8, nullable=true)
     */
    private $numtelentr;

    /**
     * @var string
     *
     * @ORM\Column(name="adresseEntr", type="string", length=20, nullable=true)
     */
    private $adresseentr;


}

