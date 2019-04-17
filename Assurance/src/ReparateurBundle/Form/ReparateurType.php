<?php

namespace ReparateurBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\EmailType;
use Symfony\Component\Form\Extension\Core\Type\NumberType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\FileType;


class ReparateurType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('cinrep',NumberType::class)->add('faxrep',NumberType::class)->add('nomrep',TextType::class)->add('prenomrep',TextType::class)->add('mailrep',EmailType::class)->add('numerorep',NumberType::class)->add('adresserep',TextType::class)->add('etatrep',ChoiceType::class,['choices'=>
            [
                'non','oui',
            ],])->add('descriptionrep',TextType::class)->add('imageFile',FileType::class, [
            'required' =>false])->add('save',SubmitType::class);
    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'ReparateurBundle\Entity\Reparateur'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'reparateurbundle_reparateur';
    }


}
