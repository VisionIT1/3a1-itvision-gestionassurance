<?php

namespace OffreBonConducteurBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class OffrebonconducteurType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('liboffre',TextType::class,array('required' => false,'label'=>'Libelle:'))->add('datedebutoffre',DateType::class,array('label'=>'Date debut:'))->add('datefinoffre',DateType::class,array('label'=>'Date Fin:'))->add('pourcentagereduction',TextType::class,array('required' => false,'label'=>'Pourcentage de réduction:'))->add('imgOff',TextType::class,array('required' => false,'label'=>'Image:'))->add('descripoffre',TextareaType::class, [
            'attr' => ['class' => 'tinymce'],
        ],array('required' => false,'label'=>'Description:'))->add('save',SubmitType::class);
    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'OffreBonConducteurBundle\Entity\Offrebonconducteur'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'offrebonconducteurbundle_offrebonconducteur';
    }


}
