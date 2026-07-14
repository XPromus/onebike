import { cva, type VariantProps } from "class-variance-authority";

export const button = cva(
    "rounded-full font-bold transition-colors duration-200 disabled:opacity-50 disabled:pointer-events-none hover:cursor-pointer",
    {
        variants: {
            variant: {
                primary: "bg-primary hover:bg-primary-hover active:bg-primary-active text-white",
                secondary: "bg-secondary hover:bg-secondary-hover active:bg-secondary-active text-white",
                panel: "bg-panel hover:bg-panel-hover active:bg-panel-active text-black",
            }, size: {
                sm: "p-2 text-sm",
                lg: "p-5 text-2xl",
            },
        }, 
        defaultVariants: { 
            variant: "primary", 
            size: "lg", 
        },
    },
)

export type ButtonVariants = VariantProps<typeof button>;
