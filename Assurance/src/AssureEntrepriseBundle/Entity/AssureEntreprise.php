<?php

namespace AssureEntrepriseBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * AssureEntreprise
 *
 * @ORM\Table(name="assure_entreprise")
 * @ORM\Entity(repositoryClass="AssureEntrepriseBundle\Repository\AssureEntrepriseRepository")
 */
class AssureEntreprise
{
    /**
     * @var string
     *
     * @ORM\Column(name="nomEntr", type="string", length=20, nullable=false)
     * @ORM\Id
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

    /**
     * @return string
     */
    public function getNomentr()
    {
        return $this->nomentr;
    }

    /**
     * @param string $nomentr
     */
    public function setNomentr($nomentr)
    {
        $this->nomentr = $nomentr;
    }

    /**
     * @return string
     */
    public function getEmailentr()
    {
        return $this->emailentr;
    }

    /**
     * @param string $emailentr
     */
    public function setEmailentr($emailentr)
    {
        $this->emailentr = $emailentr;
    }

    /**
     * @return string
     */
    public function getNumtelentr()
    {
        return $this->numtelentr;
    }

    /**
     * @param string $numtelentr
     */
    public function setNumtelentr($numtelentr)
    {
        $this->numtelentr = $numtelentr;
    }

    /**
     * @return string
     */
    public function getAdresseentr()
    {
        return $this->adresseentr;
    }

    /**
     * @param string $adresseentr
     */
    public function setAdresseentr($adresseentr)
    {
        $this->adresseentr = $adresseentr;
    }


}

