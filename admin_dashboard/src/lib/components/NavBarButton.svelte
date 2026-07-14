<script lang="ts">
    import type { Snippet } from "svelte";

    interface Props {
        type?: 'button' | 'submit' | 'reset';
        disabled?: boolean;
        href?: string;
        onclick?: (e: MouseEvent) => void;
        children: Snippet
    }

    let {
        type = 'button',
        disabled = false,
        href,
        onclick,
        children,
        ...rest
    }: Props = $props();

    const tag = $derived(href ? "a" : "button");
</script>

<svelte:element
    class="py-2 text-center transition-colors duration-200 hover:bg-mauve-200 hover:cursor-pointer active:bg-mauve-400"
    this={tag}
    href={href}
    disabled={href ? undefined : disabled}
    onclick={onclick}
    {...rest}
>
    {@render children()}
</svelte:element>
